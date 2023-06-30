package ca.bc.gov.ride.geocodersvc.controller;

import ca.bc.gov.ride.geocodersvc.config.properties.Properties;
import ca.bc.gov.ride.geocodersvc.api.AddressApi;
import ca.bc.gov.ride.geocodersvc.domain.service.BCDataService;
import ca.bc.gov.ride.geocodersvc.domain.service.GoogleService;
import ca.bc.gov.ride.geocodersvc.model.*;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@OpenAPIDefinition(servers = {@Server(url = "/", description = "Default Server URL")})
public class AddressApiController implements AddressApi {

    @Autowired
    private BCDataService bcDataService;

    @Autowired
    private GoogleService googleService;

    @Autowired
    private Properties properties;

    @Override
    public Mono<ResponseEntity<DataResponse>> addressLookup(String address, ServerWebExchange exchange) {
        return sendQuery(address)
                .map(dataBc -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(dataBc))
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build()));
    }

    public Mono<DataResponse> sendQuery(String addressString) {
        return bcDataService.getBcDataApi(addressString)
                .flatMap(dataResponse -> dataResponse.dataBc().score() < properties.getMinimumConfidenceScore() && properties.isGooglefailOver() ?
                        googleService.getGoogleApi(addressString, dataResponse.dataBc()) : Mono.just(dataResponse));
    }
}
