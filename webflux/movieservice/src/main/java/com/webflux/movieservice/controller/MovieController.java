package com.webflux.movieservice.controller;

import com.webflux.movieservice.client.MovieInfoRestClient;
import com.webflux.movieservice.client.MovieReviewRestClient;
import com.webflux.movieservice.dto.Movie;
import com.webflux.movieservice.dto.MovieInfo;
import com.webflux.movieservice.dto.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/v1/movies")
public class MovieController {

    @Autowired
    private MovieInfoRestClient movieInfoRestClient;

    @Autowired
    private MovieReviewRestClient movieReviewRestClient;


    @GetMapping("/{id}")
    public Mono<Movie> retrieveMovieById(@PathVariable("id") String id){
        return movieInfoRestClient.retrieveMovieInfo(id)
                .flatMap(movieInfo -> {
                    Mono<List<Review>> reviewMono = movieReviewRestClient.retrieveMovieInfo(id)
                            .collectList();
                    return reviewMono.map(reviews -> new Movie(movieInfo, reviews));
                });
    }


    @GetMapping(value = "/stream", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<MovieInfo> MovieInfoStream(){
        return movieInfoRestClient.retrieveMovieInfoStream();
    }

}
