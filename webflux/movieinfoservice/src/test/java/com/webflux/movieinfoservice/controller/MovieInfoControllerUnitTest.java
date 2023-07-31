package com.webflux.movieinfoservice.controller;

import com.webflux.movieinfoservice.domain.MovieInfo;
import com.webflux.movieinfoservice.service.impl.MovieInfoServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;

@WebFluxTest(controllers = MovieInfoController.class)
@AutoConfigureWebTestClient
public class MovieInfoControllerUnitTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private MovieInfoServiceImpl movieInfoService;

    static String MOVIES_INFO_URL = "/v1/movieinfos";

    @Test
    void getAllMovieInfos() {
        var movieinfos = List.of(new MovieInfo(null, "Batman Begins",
                        2005, List.of("Christian Bale", "Michael Cane"), LocalDate.parse("2005-06-15")),
                new MovieInfo(null, "The Dark Knight",
                        2008, List.of("Christian Bale", "HeathLedger"), LocalDate.parse("2008-07-18")),
                new MovieInfo("abc", "Dark Knight Rises",
                        2012, List.of("Christian Bale", "Tom Hardy"), LocalDate.parse("2012-07-20")));

        when(movieInfoService.getAllMovieInfos()).thenReturn(Flux.fromIterable(movieinfos));

        webTestClient.get()
                .uri(MOVIES_INFO_URL)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBodyList(MovieInfo.class)
                .hasSize(3);
    }


    @Test
    void getMovieInfoById() {
        var movieinfo = new MovieInfo("abc", "Dark Knight Rises",
                2012, List.of("Christian Bale", "Tom Hardy"), LocalDate.parse("2012-07-20"));

        var movieInfoId = "abc";
        when(movieInfoService.getMovieInfoById(movieInfoId)).thenReturn(Mono.just(movieinfo));


        webTestClient.get()
                .uri(MOVIES_INFO_URL + "/{id}", movieInfoId)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody()
                .jsonPath("$.name").isEqualTo("Dark Knight Rises");
//                .expectBody(MovieInfo.class)
//                .consumeWith(movieInfoEntityExchangeResult -> {
//                    MovieInfo movieInfo1 = movieInfoEntityExchangeResult.getResponseBody();
//                    assert movieInfo1 != null;
//                    assert movieInfo1.getMovieInfoId().equals("abc");
//                });
    }

    @Test
    void addMovieInfo() {

        MovieInfo movieInfo = new MovieInfo("abc", "Batman Begins1",
                2005, List.of("Christian Bale", "Michael Cane"), LocalDate.parse("2005-06-15"));

        when(movieInfoService.addMovieInfo(movieInfo)).thenReturn(Mono.just(movieInfo));

        webTestClient.post()
                .uri(MOVIES_INFO_URL)
                .bodyValue(movieInfo)
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody(MovieInfo.class)
                .consumeWith(movieInfoEntityExchangeResult -> {
                    MovieInfo movieInfo1 = movieInfoEntityExchangeResult.getResponseBody();
                    assert movieInfo1 != null;
                    assert movieInfo1.getMovieInfoId() != null;
                });
    }

    @Test
    void updateMovieInfo() {
        var movieInfoId = "abc";
        MovieInfo movieInfo = new MovieInfo(null, "Dark Knight Rises abc",
                2005, List.of("Christian Bale", "Michael Cane"), LocalDate.parse("2005-06-15"));

        when(movieInfoService.updateMovieInfo(isA(MovieInfo.class), isA(String.class))).thenReturn(Mono.just(movieInfo));

        webTestClient.put()
                .uri(MOVIES_INFO_URL + "/{id}", movieInfoId)
                .bodyValue(movieInfo)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(MovieInfo.class)
                .consumeWith(movieInfoEntityExchangeResult -> {
                    MovieInfo updateMovieInfo = movieInfoEntityExchangeResult.getResponseBody();
                    assert updateMovieInfo.getName().equals("Dark Knight Rises abc");
                });
    }

    @Test
    void deleteMovieInfo() {
        var movieInfoId = "abc";

        when(movieInfoService.deleteMovieInfo(isA(String.class))).thenReturn(Mono.empty());

        webTestClient.delete()
                .uri(MOVIES_INFO_URL + "/{id}", movieInfoId)
                .exchange()
                .expectStatus()
                .isNoContent();
    }

}
