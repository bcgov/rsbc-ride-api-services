package ca.bc.gov.ride.geocodersvc.config.filter;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.channels.Channels;
import java.nio.charset.StandardCharsets;
import java.util.Set;

@Component
@Slf4j
public class RequestResponseLoggingFilter implements WebFilter {

	private static final Set<String> URLS = Set.of("/api/v1");

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
		final var httpRequest = exchange.getRequest();
		final var httpUrl = String.valueOf(httpRequest.getURI());
		final var headers = exchange.getRequest().getHeaders();
		final var queryParams = exchange.getRequest().getQueryParams();
		ServerHttpRequestDecorator loggingServerHttpRequestDecorator =
				new ServerHttpRequestDecorator(exchange.getRequest()) {
			String requestBody = null;


			@Override
			public Flux<DataBuffer> getBody() {
				return super.getBody().doOnNext(dataBuffer -> {
					try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
						Channels.newChannel(byteArrayOutputStream).write(dataBuffer.asByteBuffer().asReadOnlyBuffer());
						requestBody = new String(byteArrayOutputStream.toByteArray(), StandardCharsets.UTF_8);
						log.debug("Request Logging filter:" + LogMessage.builder()
						.url(httpUrl)
						.headers(headers)
						.queryParams(queryParams)
						.payload(requestBody).build().toString());
					} catch (IOException ex) {
						log.error("Request Logging filter :" + LogMessage.builder()
						.url(httpUrl)
						.headers(headers)
						.queryParams(queryParams)
						.errorType("IO exception")
						.payload(ex.getMessage()).build().toString(), ex);
					}
				});
			}
		};

		ServerHttpResponseDecorator loggingServerHttpResponseDecorator =
				new ServerHttpResponseDecorator(exchange.getResponse()) {
			String responseBody = null;

			@Override
			public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
				Mono<DataBuffer> buffer = Mono.from(body);
				return super.writeWith(buffer.doOnNext(dataBuffer -> {
					try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
						Channels.newChannel(byteArrayOutputStream).write(dataBuffer.asByteBuffer().asReadOnlyBuffer());
						responseBody = new String(byteArrayOutputStream.toByteArray(), StandardCharsets.UTF_8);
						log.debug("Response Logging filter: " + LogMessage.builder()
						.url(httpUrl)
						.headers(headers)
						.queryParams(queryParams)
						.payload(responseBody).build().toString());
					} catch (IOException ex) {
						log.error("Response Logging filter: " + LogMessage.builder()
						.url(httpUrl)
						.queryParams(queryParams)
						.headers(headers)
						.errorType("IO exception")
						.payload(ex.getMessage()).build().toString(), ex);
					}
				}));
			}
		};
		return URLS.stream().anyMatch(httpUrl::contains)
				? chain.filter(
						exchange.mutate().request(loggingServerHttpRequestDecorator).response(loggingServerHttpResponseDecorator)
						.build()) : chain.filter(exchange);
	}

	@Builder
	@RequiredArgsConstructor
	@Getter
	@ToString
	public static class LogMessage {
		private final String url;
		private final HttpHeaders headers;
		private final MultiValueMap<?, ?> queryParams;
		private final String payload;
		private final String errorType;
	}
}


