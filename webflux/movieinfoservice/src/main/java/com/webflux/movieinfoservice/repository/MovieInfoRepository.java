package com.webflux.movieinfoservice.repository;

import com.webflux.movieinfoservice.domain.MovieInfo;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface MovieInfoRepository extends ReactiveMongoRepository<MovieInfo, String> {

    Flux<Object> findByYear(int year);

    Flux<Object> findByName(String name);
}
