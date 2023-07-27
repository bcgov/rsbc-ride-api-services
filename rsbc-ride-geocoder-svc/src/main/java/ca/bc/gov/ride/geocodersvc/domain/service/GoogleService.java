package ca.bc.gov.ride.geocodersvc.domain.service;

import ca.bc.gov.ride.geocodersvc.domain.model.google.GoogleRaw;
import ca.bc.gov.ride.geocodersvc.config.properties.Properties;
import ca.bc.gov.ride.geocodersvc.model.DataBc;
import ca.bc.gov.ride.geocodersvc.model.DataResponse;
import ca.bc.gov.ride.geocodersvc.model.GoogleData;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

import static ca.bc.gov.ride.geocodersvc.domain.enums.GoogleScoreEnum.getEnumfromLocationType;

@Slf4j
@Service
public class GoogleService {

    @Autowired
    private WebClient webClient;

    @Autowired
    private Properties properties;

    @WithSpan
    public Mono<DataResponse> getGoogleApi(String addressString, DataBc dataBc) {
        return webClient
                .method(HttpMethod.GET)
                .uri(properties.getGoogleUrl() +
                        "?key=" + googleApiKeyValidation(properties.getGoogleApiKey()) +
                        "&address=" + URLEncoder.encode(addressString, StandardCharsets.UTF_8))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(GoogleRaw.class)
                .map(googleData -> DataResponse.builder()
                        .success(true)
                        .addressRaw(addressString)
                        .dataBc(dataBc)
                        .google(extractGoogleData(googleData))
                        .build())

                .onErrorResume(Exception.class, e -> getErrorResponse("response from Google Api did not match expected format: %s", e))
                .onErrorResume(WebClientResponseException.class, e -> getErrorResponse("response from Google API took too long: %s", e))
                .onErrorResume(WebClientException.class, e -> getErrorResponse("no response from the Google API: %s", e));
    }

    private GoogleData extractGoogleData(GoogleRaw googleData) {
        return GoogleData.builder()
                .lat(googleData.results().get(0).geometry().location().lat())
                .lon(googleData.results().get(0).geometry().location().lng())
                .score(getEnumfromLocationType(googleData.results().get(0).geometry().locationType()).score())
                .build();
    }

    private String googleApiKeyValidation(String googleApiKey) {
        Pattern pattern = Pattern.compile("^[A-Za-z0-9-_]{39}$");
        if (pattern.matcher(googleApiKey).matches()){
            return googleApiKey;
        }
        else {
            throw new RuntimeException("Google API Key is invalid");
        }
    }

    @NotNull
    private static Mono<DataResponse> getErrorResponse(String message, Exception e) {
        log.error(String.format(message, e.getMessage()));
        return Mono.just(DataResponse.builder().success(false)
                .error("Google Api did not return a successful response").build());
    }
}
