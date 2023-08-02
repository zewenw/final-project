package com.webflux.movieinfoservice.controller;

import com.webflux.movieinfoservice.domain.MovieInfo;
import com.webflux.movieinfoservice.service.impl.MovieInfoServiceImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

@RestController
@RequestMapping("/v1")
@Slf4j
public class MovieInfoController {

    @Autowired
    private MovieInfoServiceImpl movieInfoService;

    Sinks.Many<MovieInfo> movieInfoSink = Sinks.many().replay().all();

    //add save
    @PostMapping("/movieinfos")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<MovieInfo> addMovieInfo(@RequestBody @Valid MovieInfo movieInfo) {
        return movieInfoService.addMovieInfo(movieInfo)
                .doOnNext(savedInfo -> movieInfoSink.tryEmitNext(savedInfo))
                .log();
    }

    //get query
    @GetMapping(value = "/movieinfos/stream", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<MovieInfo> stream() {
        return movieInfoSink.asFlux().log();
    }

    //get query
    @GetMapping("/movieinfos")
    public Flux<MovieInfo> getAllMovieInfos(@RequestParam(value = "year", required = false) Integer year,
                                            @RequestParam(value = "name", required = false) String name) {
        log.info("com.webflux.movieinfoservice.controller.MovieInfoController.getAllMovieInfos, year:{}", year);
        log.info("com.webflux.movieinfoservice.controller.MovieInfoController.getAllMovieInfos, name:{}", name);
        if(!ObjectUtils.isEmpty(name)){
            return movieInfoService.getMovieInfoByName(name);
        }
        if(year != null){
            return movieInfoService.getMovieInfoByYear(year);
        }
        return movieInfoService.getAllMovieInfos().log();
    }

    //get query
    @GetMapping("/movieinfos/{id}")
    public Mono<ResponseEntity<MovieInfo>> getMovieInfoById(@PathVariable String id) {
        return movieInfoService.getMovieInfoById(id)
                .map(ResponseEntity.ok()::body)
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()))
                .log();
    }

    //update modify
    @PutMapping("/movieinfos/{id}")
    public Mono<ResponseEntity<MovieInfo>> updateMovieInfo(@RequestBody MovieInfo updateMovieInfo, @PathVariable String id) {
        return movieInfoService.updateMovieInfo(updateMovieInfo, id)
                .map(ResponseEntity.ok()::body)
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()))
                .log();
    }

    //delete remove
    @DeleteMapping("/movieinfos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteMovieInfo(@PathVariable String id) {
        return movieInfoService.deleteMovieInfo(id).log();
    }
}
