package com.webflux.movieinfoservice.service.impl;

import com.webflux.movieinfoservice.domain.MovieInfo;
import com.webflux.movieinfoservice.repository.MovieInfoRepository;
import com.webflux.movieinfoservice.service.MovieInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MovieInfoServiceImpl implements MovieInfoService {

    @Autowired
    private MovieInfoRepository movieInfoRepository;


    @Override
    public Mono<MovieInfo> addMovieInfo(MovieInfo movieInfo) {
        return movieInfoRepository.save(movieInfo);
    }

    @Override
    public Flux<MovieInfo> getAllMovieInfos() {
        return movieInfoRepository.findAll();
    }

    @Override
    public Mono<MovieInfo> getMovieInfoById(String id) {
        return movieInfoRepository.findById(id);
    }

    @Override
    public  Mono<MovieInfo> updateMovieInfo(MovieInfo updateMovieInfo, String id) {
        return movieInfoRepository.findById(id)
                /**
                 * if change flatmap to map, then it doesn't work because movieInfoRepository.save(movieInfo)
                 * return Mono<MovieInfo>, with map operator, the whole snippet will return Mono<Mono<MovieInfo>
                 * but if using flatMap operator, the result will be flatten, so the result will be Mono<MovieInfo>
                 */
                .flatMap(movieInfo ->{
                    movieInfo.setCast(updateMovieInfo.getCast());
                    movieInfo.setName(updateMovieInfo.getName());
                    movieInfo.setRelease_date(updateMovieInfo.getRelease_date());
                    movieInfo.setYear(updateMovieInfo.getYear());
                    return movieInfoRepository.save(movieInfo);
                });
    }

    @Override
    public Mono<Void> deleteMovieInfo(String id) {
        return movieInfoRepository.deleteById(id);
    }

    @Override
    public Flux<MovieInfo> getMovieInfoByYear(Integer year) {
        return movieInfoRepository.findByYear(year);
    }

    @Override
    public Flux<MovieInfo> getMovieInfoByName(String name) {
        return movieInfoRepository.findByName(name);
    }
}
