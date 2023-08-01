package com.webflux.moviereviewservice.handler;

import com.webflux.moviereviewservice.domain.Review;
import com.webflux.moviereviewservice.repository.ReviewReactiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Nonnull;
import java.util.Optional;

@Component
public class ReviewHandler {

    @Autowired
    private ReviewReactiveRepository reviewReactiveRepository;

    @Nonnull
    public Mono<ServerResponse> addReview(ServerRequest request) {
        return request.bodyToMono(Review.class)
                .flatMap(review -> reviewReactiveRepository.save(review))
                .flatMap(ServerResponse.status(HttpStatus.CREATED)::bodyValue);
    }

    @Nonnull
    public Mono<ServerResponse> getReviews(ServerRequest request) {
        Optional<String> movieInfoId = request.queryParam("movieInfoId");
        Flux<Review> reviewFlux;
        if(movieInfoId.isPresent()){
            reviewFlux = reviewReactiveRepository.findByMovieInfoId(Long.valueOf(movieInfoId.get()));
        }else {
            reviewFlux = reviewReactiveRepository.findAll();
        }
        return ServerResponse.ok().body(reviewFlux, Review.class);
    }

    @Nonnull
    public Mono<ServerResponse> updateReview(ServerRequest request) {
        String reviewId = request.pathVariable("id");
        Mono<Review> reviewMono = reviewReactiveRepository.findById(reviewId);
        return reviewMono.flatMap(review -> request.bodyToMono(Review.class)
                .map(reqReview ->{
                    review.setComment(reqReview.getComment());
                    review.setRating(reqReview.getRating());
                    review.setMovieInfoId(reqReview.getMovieInfoId());
                    return review;
                })
        .flatMap(reviewReactiveRepository::save)
        .flatMap(savedReview -> ServerResponse.ok().bodyValue(savedReview)));
    }

    @Nonnull
    public Mono<ServerResponse> deleteReview(ServerRequest request) {
        String reviewId = request.pathVariable("id");
        Mono<Review> reviewMono = reviewReactiveRepository.findById(reviewId);
        return reviewMono.flatMap(review -> reviewReactiveRepository.deleteById(reviewId))
                .then(ServerResponse.noContent().build());
    }
}
