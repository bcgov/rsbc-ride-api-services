package ca.bc.gov.ride.geocodersvc.domain.service;

import ca.bc.gov.ride.geocodersvc.domain.model.dataBc.DataBcRaw;
import ca.bc.gov.ride.geocodersvc.config.properties.Properties;
import ca.bc.gov.ride.geocodersvc.model.DataBc;
import ca.bc.gov.ride.geocodersvc.model.DataResponse;
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
import java.time.Duration;
import java.util.Collections;

@Slf4j
@Service
public class BCDataService {

    @Autowired
    private WebClient webClient;

    @Autowired
    private Properties properties;

    @WithSpan
    public Mono<DataResponse> getBcDataApi(String addressString) {
        // log the string
        log.info("addressString: " + addressString);
        log.info("url: " + properties.getDataBcUrl() + "/addresses.json?addressString=" +
                URLEncoder.encode(addressString, StandardCharsets.UTF_8));
        return webClient
                .method(HttpMethod.GET)
                .uri(properties.getDataBcUrl() + "/addresses.json?addressString=" + addressString)
//                        URLEncoder.encode(addressString, StandardCharsets.UTF_8))
                .header("apikey", properties.getDataBcApiKey())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(DataBcRaw.class)
//                .timeout(Duration.ofSeconds(60))
                .map(data -> DataResponse.builder()
                        .success(true)
                        .addressRaw(addressString)
                        .dataBc(extractDataBc(data))
                        .build())
                .onErrorResume(Exception.class, e -> getErrorResponse("response from DataBC did not match expected format: %s", e))
                .onErrorResume(WebClientResponseException.class, e -> getErrorResponse("response from DataBC took too long: %s", e))
                .onErrorResume(WebClientException.class, e -> getErrorResponse("no response from the DataBC API: %s", e));
    }

    private DataBc extractDataBc(DataBcRaw data) {

        return DataBc.builder()
                .fullAddress(data.features().get(0).properties().fullAddress())
                .score(data.features().get(0).properties().score())
                .precision(data.features().get(0).properties().matchPrecision())
                .faults(Collections.singletonList(data.features().get(0).properties().faults()))
                .lat(data.features().get(0).geometry().coordinates().get(1))
                .lon(data.features().get(0).geometry().coordinates().get(0))
                .build();
    }

    @NotNull
    private static Mono<DataResponse> getErrorResponse(String message, Exception e) {
        log.error(String.format(message, e.getMessage()));
        return Mono.just(DataResponse.builder().success(false)
                .error("DataBC did not return a successful response").build());
    }
}
