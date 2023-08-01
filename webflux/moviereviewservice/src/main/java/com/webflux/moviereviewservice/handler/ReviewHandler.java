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

@Component
public class ReviewHandler {

    @Autowired
    private ReviewReactiveRepository reviewReactiveRepository;

    public Mono<ServerResponse> addReview(ServerRequest request) {
        return request.bodyToMono(Review.class)
                .flatMap(review -> reviewReactiveRepository.save(review))
                .flatMap(ServerResponse.status(HttpStatus.CREATED)::bodyValue);
    }

    public Mono<ServerResponse> getReviews(ServerRequest request) {
        Flux<Review> reviewFlux = reviewReactiveRepository.findAll();
        return ServerResponse.ok().body(reviewFlux, Review.class);
    }
}
