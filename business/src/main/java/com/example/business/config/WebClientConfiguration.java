package com.example.business.config;

import com.example.daoService.exceptions.CustomException;
import com.example.daoService.exceptions.Status431PaymentNotFoundException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Configuration
public class WebClientConfiguration {
    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl("http://localhost:8080")
                .filter((request, next) -> next.exchange(request)
                        .flatMap(this::handleErrors))
                .build();
    }

    private Mono<ClientResponse> handleErrors(ClientResponse response) {
        if (response.statusCode().isError()) {
            return response.bodyToMono(String.class)
                    .flatMap(errorBody -> Mono.error(new CustomException(response.statusCode(), errorBody)));
        }
        return Mono.just(response);
    }
}