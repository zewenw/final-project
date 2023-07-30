package com.webflux.reactor.example;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

class FluxAndMonoGeneratorServiceTest {

    FluxAndMonoGeneratorService testService = new FluxAndMonoGeneratorService();

    @Test
    void namesFlux() {
        //given

        //when
        var nameFlux = testService.nameFlux();
        //then
        StepVerifier.create(nameFlux)
//                .expectNext("alex", "ben", "chloe")
//                .expectNextCount(3)
                .expectNext("alex")
                .expectNextCount(2)
                .verifyComplete();
    }

    @Test
    void nameFluxImmutability() {
        //given

        //when
        var nameFlux = testService.nameFluxImmutability();
        //then
        StepVerifier.create(nameFlux)
                .expectNext("ALEX", "BEN", "CHLOE")
                .verifyComplete();
    }

    @Test
    void nameFluxFlatMap() {
        //given

        //when
        var nameFlux = testService.nameFluxFlatMap();
        //then
        StepVerifier.create(nameFlux)
                .expectNext("A", "L", "E", "X")
                .verifyComplete();
    }

    @Test
    void nameFluxFlatMapAsync() {
        //given

        //when
        var nameFlux = testService.nameFluxFlatMapAsync();
        //then
        StepVerifier.create(nameFlux)
//                .expectNext("A", "L", "E", "X", "C", "H", "L", "O", "E")
                .expectNextCount(9)
                .verifyComplete();
    }

    @Test
    void nameFluxConcatMap() {
        //given

        //when
        var nameFlux = testService.nameFluxConcatMap();
        //then
        StepVerifier.create(nameFlux)
                .expectNext("A", "L", "E", "X", "C", "H", "L", "O", "E")
//                .expectNextCount(9)
                .verifyComplete();
    }

    @Test
    void nameMonoFlatMap() {
        //given

        //when
        Mono<List<String>> mono = testService.nameMonoFlatMap(3);
        //then
        StepVerifier.create(mono)
                .expectNext(List.of("A", "L", "E", "X"))
//                .expectNextCount(9)
                .verifyComplete();
    }

    @Test
    void nameMonoFlatMapMany() {
        //given

        //when
        Flux<String> flux = testService.nameMonoFlatMapMany(3);
        //then
        StepVerifier.create(flux)
                .expectNext("A", "L", "E", "X")
//                .expectNextCount(9)
                .verifyComplete();
    }

    @Test
    void nameFluxTransform() {
        //given

        //when
        Flux<String> flux = testService.nameFluxTransform(3);
        //then
        StepVerifier.create(flux)
                .expectNext("A", "L", "E", "X", "C", "H", "L", "O", "E")
//                .expectNextCount(9)
                .verifyComplete();
    }

    @Test
    void nameFluxTransform1() {
        //given

        //when
        Flux<String> flux = testService.nameFluxTransform(6);
        //then
        StepVerifier.create(flux)
//                .expectNext("A", "L", "E", "X", "C", "H", "L", "O", "E")
//                .expectNextCount(9)
                .expectNext("default")
                .verifyComplete();
    }

    @Test
    void nameFluxTransformSwitchIfEmpty() {
        //given

        //when
        Flux<String> flux = testService.nameFluxTransformSwitchIfEmpty(6);
        //then
        StepVerifier.create(flux)
//                .expectNext("A", "L", "E", "X", "C", "H", "L", "O", "E")
//                .expectNextCount(9)
                .expectNext("D", "E", "F", "A", "U", "L", "T")
                .verifyComplete();
    }

    @Test
    void concatFlux() {
        //given

        //when
        Flux<String> flux = testService.concatFlux();
        //then
        StepVerifier.create(flux)
//                .expectNext("A", "L", "E", "X", "C", "H", "L", "O", "E")
//                .expectNextCount(9)
                .expectNext("A", "B", "C", "D", "E", "F")
                .verifyComplete();
    }

    @Test
    void concatWithMono() {
        //given

        //when
        Flux<String> flux = testService.concatWithMono();
        //then
        StepVerifier.create(flux)
//                .expectNext("A", "L", "E", "X", "C", "H", "L", "O", "E")
//                .expectNextCount(9)
                .expectNext("A", "B", "C", "D")
                .verifyComplete();
    }

    @Test
    void mergeFlux() {
        //given

        //when
        Flux<String> flux = testService.mergeFlux();
        //then
        StepVerifier.create(flux)
//                .expectNext("A", "L", "E", "X", "C", "H", "L", "O", "E")
//                .expectNextCount(9)
                .expectNext("A", "D", "B", "E", "C", "F")
                .verifyComplete();
    }

    @Test
    void mergeSequentialFlux() {
        //given

        //when
        Flux<String> flux = testService.mergeSequentialFlux();
        //then
        StepVerifier.create(flux)
//                .expectNext("A", "L", "E", "X", "C", "H", "L", "O", "E")
//                .expectNextCount(9)
                .expectNext("A", "B", "C", "D", "E", "F")
                .verifyComplete();
    }

    @Test
    void zipOperator() {
        //given

        //when
        Flux<String> flux = testService.zipOperator();
        //then
        StepVerifier.create(flux)
                .expectNext("AD", "BE", "CF", "G")
//                .expectNext("AD", "BE", "CF")
                .verifyComplete();
    }

    @Test
    public void test() {
        Flux<Integer> flux1 = Flux.range(1, 4).hide().log("\tFLUX 1");
        Flux<Integer> flux2 = Flux.range(10, 2).hide().log("\tFlux 2");
        Flux<String> abcFlux = Flux.just("A", "B", "C");
        Flux<String> defFlux = Flux.just("D", "E", "F");
        abcFlux.zipWith(flux2, 1)
                .log("zipped")
                .blockLast();
    }
}