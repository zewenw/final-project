package com.webflux.reactor.test;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class SinkTest {

    @Test
    void sink() {
        //given
        Sinks.Many<Integer> replaySinks = Sinks.many().replay().all();

        //when
        replaySinks.emitNext(1, Sinks.EmitFailureHandler.FAIL_FAST);
        replaySinks.emitNext(2, Sinks.EmitFailureHandler.FAIL_FAST);

        //then
        Flux<Integer> integerFlux1 = replaySinks.asFlux();
        integerFlux1.subscribe(item -> System.out.println("Subscribe1 i : " + item));
        Flux<Integer> integerFlux2 = replaySinks.asFlux();
        integerFlux2.subscribe(item -> System.out.println("Subscribe2 i : " + item));


        replaySinks.tryEmitNext(3);

        Flux<Integer> integerFlux3 = replaySinks.asFlux();
        integerFlux3.subscribe(item -> System.out.println("Subscribe3 i : " + item));
    }

    @Test
    void sinksMultiCast() {
        //given
        Sinks.Many<Integer> multicast = Sinks.many().multicast().onBackpressureBuffer();

        //when
        multicast.emitNext(1, Sinks.EmitFailureHandler.FAIL_FAST);
        multicast.emitNext(2, Sinks.EmitFailureHandler.FAIL_FAST);

        //then
        Flux<Integer> integerFlux1 = multicast.asFlux();
        integerFlux1.subscribe(item -> System.out.println("Subscribe1 i : " + item));
        Flux<Integer> integerFlux2 = multicast.asFlux();
        integerFlux2.subscribe(item -> System.out.println("Subscribe2 i : " + item));


        multicast.tryEmitNext(3);

//        Flux<Integer> integerFlux3 = multicast.asFlux();
//        integerFlux3.subscribe(item -> System.out.println("Subscribe3 i : " + item));
    }

    @Test
    void sinksUniCast() {
        //given
        Sinks.Many<Integer> unicast = Sinks.many().unicast().onBackpressureBuffer();

        //when
        unicast.emitNext(1, Sinks.EmitFailureHandler.FAIL_FAST);
        unicast.emitNext(2, Sinks.EmitFailureHandler.FAIL_FAST);

        //then
        Flux<Integer> integerFlux1 = unicast.asFlux();
        integerFlux1.subscribe(item -> System.out.println("Subscribe1 i : " + item));
//        Flux<Integer> integerFlux2 = unicast.asFlux();
//        integerFlux2.subscribe(item -> System.out.println("Subscribe2 i : " + item));


        unicast.tryEmitNext(3);

//        Flux<Integer> integerFlux3 = multicast.asFlux();
//        integerFlux3.subscribe(item -> System.out.println("Subscribe3 i : " + item));
    }
}
