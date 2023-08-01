package com.webflux.moviereviewservice.repository;

import com.webflux.moviereviewservice.domain.Review;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ReviewReactiveRepository extends ReactiveMongoRepository<Review, String> {
}
