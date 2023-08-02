package com.webflux.movieservice.client;


import com.webflux.movieservice.dto.MovieInfo;
import com.webflux.movieservice.exception.MoviesInfoClientException;
import com.webflux.movieservice.exception.MoviesInfoServerException;
import com.webflux.movieservice.util.RetryUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class MovieInfoRestClient {

    @Autowired
    private WebClient webClient;

    @Value("${restclient.movieInfoUrl}")
    private String moviesInfoUrl;

    public Mono<MovieInfo> retrieveMovieInfo(String movieId) {
        var url = moviesInfoUrl.concat("/{id}");

        return webClient
                .get()
                .uri(url, movieId)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> {
                    log.info("Status code : {}", clientResponse.statusCode().value());
                    if (clientResponse.statusCode().equals(HttpStatus.NOT_FOUND)) {
                        return Mono.error(new MoviesInfoClientException(
                                "There is no MovieInfo available for the passed in Id : " + movieId,
                                clientResponse.statusCode().value()));
                    }
                    return clientResponse.bodyToMono(String.class)
                            .flatMap(response -> Mono.error(new MoviesInfoClientException(
                                    response,
                                    clientResponse.statusCode().value())));

                })
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> {
                    log.info("Status code : {}", clientResponse.statusCode().value());
                    return clientResponse.bodyToMono(String.class)
                            .flatMap(response -> Mono.error(new MoviesInfoServerException(
                                    "Sever Exception in Movie Info Server" + response)));

                })
                .bodyToMono(MovieInfo.class)
//                .retry(3)
                .retryWhen(RetryUtil.retrySpec())
                .log();
    }

    public Flux<MovieInfo> retrieveMovieInfoStream() {
        var url = moviesInfoUrl.concat("/stream");

        return webClient
                .get()
                .uri(url)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> {
                    log.info("Status code : {}", clientResponse.statusCode().value());

                    return clientResponse.bodyToMono(String.class)
                            .flatMap(response -> Mono.error(new MoviesInfoClientException(
                                    response,
                                    clientResponse.statusCode().value())));

                })
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> {
                    log.info("Status code : {}", clientResponse.statusCode().value());
                    return clientResponse.bodyToMono(String.class)
                            .flatMap(response -> Mono.error(new MoviesInfoServerException(
                                    "Sever Exception in Movie Info Server" + response)));

                })
                .bodyToFlux(MovieInfo.class)
//                .retry(3)
                .retryWhen(RetryUtil.retrySpec())
                .log();
    }
}
