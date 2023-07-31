package com.webflux.movieinfoservice.service;

import com.webflux.movieinfoservice.domain.MovieInfo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MovieInfoService {

    Mono<MovieInfo> addMovieInfo(MovieInfo movieInfo);

    Flux<MovieInfo> getAllMovieInfos();

    Mono<MovieInfo> getMovieInfoById(String id);

    Mono<MovieInfo> updateMovieInfo(MovieInfo movieInfo, String id);

    Mono<Void> deleteMovieInfo(String id);
}
