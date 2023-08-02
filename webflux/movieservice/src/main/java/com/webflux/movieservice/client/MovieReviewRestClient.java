package com.webflux.movieservice.client;

import com.webflux.movieservice.dto.Review;
import com.webflux.movieservice.exception.ReviewsClientException;
import com.webflux.movieservice.exception.ReviewsServerException;
import com.webflux.movieservice.util.RetryUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class MovieReviewRestClient {

    @Autowired
    private WebClient webClient;

    @Value("${restclient.movieReviewUrl}")
    private String movieReviewUrl;

    public Flux<Review> retrieveMovieInfo(String movieId) {
        String url = UriComponentsBuilder.fromHttpUrl(movieReviewUrl)
                .queryParam("movieInfoId", movieId)
                .buildAndExpand().toUriString();
        return webClient
                .get()
                .uri(url)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> {
                    log.info("Status code : {}", clientResponse.statusCode().value());
                    if (clientResponse.statusCode().equals(HttpStatus.NOT_FOUND)) {
                        return Mono.empty();
                    }
                    return clientResponse.bodyToMono(String.class)
                            .flatMap(response -> Mono.error(new ReviewsClientException(
                                    response)));
                })
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> {
                    log.info("Status code : {}", clientResponse.statusCode().value());
                    return clientResponse.bodyToMono(String.class)
                            .flatMap(response -> Mono.error(new ReviewsServerException(
                                    "Sever Exception in Review Server" + response)));

                })
                .bodyToFlux(Review.class)
//                .retry(3)
                .retryWhen(RetryUtil.retrySpec())
                .log();
    }
}
