package com.webflux.movieservice.util;

import com.webflux.movieservice.exception.MoviesInfoServerException;
import com.webflux.movieservice.exception.ReviewsServerException;
import reactor.core.Exceptions;
import reactor.util.retry.Retry;

import java.time.Duration;

public class RetryUtil {

    public static Retry retrySpec() {
        return Retry.fixedDelay(3, Duration.ofSeconds(1))
                .filter(ex -> ex instanceof MoviesInfoServerException ||
                        ex instanceof ReviewsServerException)
                .onRetryExhaustedThrow(((retryBackoffSpec, retrySignal)
                        -> Exceptions.propagate(retrySignal.failure())));
    }
}
