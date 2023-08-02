package com.webflux.movieservice.controller;

import com.webflux.movieservice.dto.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Objects;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureWebClient
@AutoConfigureWireMock(port = 8084) // automaticaly spins up a httpserver in port 8084
@TestPropertySource(properties = {
        "restclient.movieInfoUrl=http://localhost:8084/v1/movieinfos",
        "restclient.movieReviewUrl=http://localhost:8084/v1/reviews",
})
class MovieControllerIntegrationTest {

    @Autowired
    WebTestClient webTestClient;

    @Test
    void retrieveMovieById() {
        //given
        var movieId = "abc";
        stubFor(get(urlEqualTo("/v1/movieinfos" + "/" + movieId))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("movieinfo.json")));
        stubFor(get(urlPathEqualTo("/v1/reviews"))
//                .withQueryParam("movieInfoId", equalTo(movieId))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("reviews.json")));

        //when
        webTestClient
                .get()
                .uri("/v1/movies/{id}", movieId)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Movie.class)
                .consumeWith(movieEntityExchangeResult -> {
                    var movie = movieEntityExchangeResult.getResponseBody();
                    assert Objects.requireNonNull(movie).getReviewList().size() == 2;
                    assertEquals("Batman Begins", movie.getMovieInfo().getName());
                });
    }

    @Test
    void retrieveMovieByIdInfo404() {
        //given
        var movieId = "abc";
        stubFor(get(urlEqualTo("/v1/movieinfos" + "/" + movieId))
                .willReturn(aResponse()
                        .withStatus(404)));
        stubFor(get(urlPathEqualTo("/v1/reviews"))
//                .withQueryParam("movieInfoId", equalTo(movieId))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("reviews.json")));

        //when
        webTestClient
                .get()
                .uri("/v1/movies/{id}", movieId)
                .exchange()
                .expectStatus().is4xxClientError()
                .expectBody(String.class)
                .isEqualTo("There is no MovieInfo available for the passed in Id : abc");
    }

    @Test
    void retrieveMovieByIdReview404() {
        //given
        var movieId = "abc";
        stubFor(get(urlEqualTo("/v1/movieinfos" + "/" + movieId))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("movieinfo.json")));
        stubFor(get(urlPathEqualTo("/v1/reviews"))
//                .withQueryParam("movieInfoId", equalTo(movieId))
                .willReturn(aResponse()
                        .withStatus(404)));

        //when
        webTestClient
                .get()
                .uri("/v1/movies/{id}", movieId)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Movie.class)
                .consumeWith(movieEntityExchangeResult -> {
                    var movie = movieEntityExchangeResult.getResponseBody();
                    assert Objects.requireNonNull(movie).getReviewList().size() == 0;
                });
    }


    @Test
    void retrieveMovieByIdInfo500() {
        //given
        var movieId = "abc";
        stubFor(get(urlEqualTo("/v1/movieinfos" + "/" + movieId))
                .willReturn(aResponse()
                        .withStatus(500)
                        .withBody("MovieInfo Service Unavailable")));
//        stubFor(get(urlPathEqualTo("/v1/reviews"))
////                .withQueryParam("movieInfoId", equalTo(movieId))
//                .willReturn(aResponse()
//                        .withHeader("Content-Type", "application/json")
//                        .withBodyFile("reviews.json")));

        //when
        webTestClient
                .get()
                .uri("/v1/movies/{id}", movieId)
                .exchange()
                .expectStatus().is5xxServerError()
                .expectBody(String.class)
                .isEqualTo("Sever Exception in Movie Info ServerMovieInfo Service Unavailable");
    }
}