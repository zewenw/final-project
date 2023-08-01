package com.webflux.moviereviewservice.router;

import com.webflux.moviereviewservice.domain.Review;
import com.webflux.moviereviewservice.exception.handler.GlobalExceptionHandler;
import com.webflux.moviereviewservice.handler.ReviewHandler;
import com.webflux.moviereviewservice.repository.ReviewReactiveRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;

@WebFluxTest
//@ComponentScan(basePackages = {"com.webflux.moviereviewservice"})
@ContextConfiguration(classes = {ReviewHandler.class, ReviewRouter.class, GlobalExceptionHandler.class})
@AutoConfigureWebTestClient
class ReviewRouterUnitTest {

    @Autowired
    WebTestClient webTestClient;

    @MockBean
    ReviewReactiveRepository reviewReactiveRepository;

    static final String REVIEWS_URL = "/v1/reviews";

    @Test
    void reviewsRoute() {
        //given
        Review review = new Review(null, 1L, "Awesome Movie", 9.0);
        //when
        webTestClient.post()
                .uri(REVIEWS_URL)
                .bodyValue(review)
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody(Review.class)
                .consumeWith(reviewEntityExchangeResult -> {
                    Review responseBody = reviewEntityExchangeResult.getResponseBody();
                    assert responseBody != null;
                    assert responseBody.getReviewId() != null;
                });
        //then
    }


    @Test
    void getReviewsByMovieInfoId() {
        //given

        //when
        webTestClient
                .get()
                .uri(uriBuilder -> {
                    return uriBuilder.path(REVIEWS_URL)
                            .queryParam("movieInfoId", "1")
                            .build();
                })
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBodyList(Review.class)
                .value(reviewList -> {
                    System.out.println("reviewList : " + reviewList);
                    assertEquals(2, reviewList.size());
                });

    }

    @Test
    void addReview() {
        //given
        var review = new Review("12", 1L, "Awesome Movie", 9.0);
        //when
        when(reviewReactiveRepository.save(isA(Review.class))).thenReturn(Mono.just(review));
        webTestClient
                .post()
                .uri(REVIEWS_URL)
                .bodyValue(review)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Review.class)
                .consumeWith(reviewResponse -> {
                    var savedReview = reviewResponse.getResponseBody();
                    assert savedReview != null;
                    assertNotNull(savedReview.getReviewId());
                });

    }

    @Test
    void addReviewValidation() {
        //given
        var review = new Review("12", null, "Awesome Movie", -9.0);
        //when
        when(reviewReactiveRepository.save(isA(Review.class))).thenReturn(Mono.just(review));
        webTestClient
                .post()
                .uri(REVIEWS_URL)
                .bodyValue(review)
                .exchange()
                .expectStatus()
                .isBadRequest()
                .expectBody(String.class)
                .isEqualTo("rating.movieInfoId : must not be null,rating.negative : please pass a non-negative value");
    }

    @Test
    void updateReview() {
        //given
        var review = new Review("1", 1L, "Awesome Movie", 9.0);
        var reviewUpdate = new Review("1", 1L, "Not an Awesome Movie", 8.0);
        //when
        when(reviewReactiveRepository.save(isA(Review.class))).thenReturn(Mono.just(reviewUpdate));
        when(reviewReactiveRepository.findById(isA(String.class))).thenReturn(Mono.just(review));
        webTestClient
                .put()
                .uri(REVIEWS_URL + "/{id}", reviewUpdate.getReviewId())
                .bodyValue(reviewUpdate)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Review.class)
                .consumeWith(reviewResponse -> {
                    var updatedReview = reviewResponse.getResponseBody();
                    assert updatedReview != null;
                    System.out.println("updatedReview : " + updatedReview);
                    assertNotNull(review.getReviewId());
                    assertEquals(8.0, updatedReview.getRating());
                    assertEquals("Not an Awesome Movie", updatedReview.getComment());
                });
    }

    @Test
    void updateReviewWithNotFound() {
        //given
        var reviewUpdate = new Review(null, 1L, "Not an Awesome Movie", 8.0);
        //when
        when(reviewReactiveRepository.findById(isA(String.class))).thenReturn(Mono.empty());
        webTestClient
                .put()
                .uri(REVIEWS_URL + "/{id}", "100")
                .bodyValue(reviewUpdate)
                .exchange()
                .expectStatus()
                .isNotFound()
                .expectBody(String.class)
                .isEqualTo("Review Not Found for the given review id 100");
    }

    @Test
    void updateReview_NotFound() {
        //given
        var reviewUpdate = new Review(null, 1L, "Not an Awesome Movie", 8.0);
        //when
        webTestClient
                .put()
                .uri(REVIEWS_URL + "/{id}", "abc")
                .bodyValue(reviewUpdate)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void deleteReview() {
        //given
        var review = new Review(null, 1L, "Awesome Movie", 9.0);
        var savedReview = reviewReactiveRepository.save(review).block();
        //when
        assert savedReview != null;
        webTestClient
                .delete()
                .uri(REVIEWS_URL + "/{id}", savedReview.getReviewId())
                .exchange()
                .expectStatus().isNoContent();
    }
}