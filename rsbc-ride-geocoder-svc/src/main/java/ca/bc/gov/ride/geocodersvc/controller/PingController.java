package ca.bc.gov.ride.geocodersvc.controller;

import ca.bc.gov.ride.geocodersvc.api.PingApi;
import ca.bc.gov.ride.geocodersvc.model.Ping200Response;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@OpenAPIDefinition(servers = {@Server(url = "/", description = "Default Server URL")})
public class PingController implements PingApi {

    @Override
    public Mono<ResponseEntity<Ping200Response>> ping(ServerWebExchange exchange){
        return Mono.just(ResponseEntity.ok(Ping200Response.builder().message("Welcome to Geocodersvc!").build()));
    }
}
