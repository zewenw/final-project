package com.webflux.reactor.example;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class FluxAndMonoGeneratorService {

    public Flux<String> nameFlux() {
        return Flux.fromIterable(List.of("alex", "ben", "chloe")).log();
    }

    public Flux<String> nameFluxFlatMap() {
        return Flux.fromIterable(List.of("alex"))
                .map(String::toUpperCase)
                .flatMap(this::splitString)
                .log();
    }

    public Flux<String> splitString(String name) {
        String[] split = name.split("");
        return Flux.fromArray(split);
    }

    public Flux<String> nameFluxFlatMapAsync() {
        return Flux.fromIterable(List.of("alex", "chloe"))
                .map(String::toUpperCase)
                .flatMap(this::splitStringWithDelay)
                .log();
    }

    public Flux<String> nameFluxConcatMap() {
        return Flux.fromIterable(List.of("alex", "chloe"))
                .map(String::toUpperCase)
                .concatMap(this::splitStringWithDelay)
                .log();
    }

    public Flux<String> splitStringWithDelay(String name) {
        String[] split = name.split("");
//        int delay = new Random().nextInt(1000);
        int delay = 1000;
        return Flux.fromArray(split).delayElements(Duration.ofMillis(delay));
    }


    public Flux<String> nameFluxImmutability() {
        Flux<String> flux = Flux.fromIterable(List.of("alex", "ben", "chloe"));
//        flux.map(String::toUpperCase);
        Flux<String> map = flux.map(String::toUpperCase);
        return map;
    }


    public Flux<String> nameFluxTransform(int stringLength) {
        Function<Flux<String>,Flux<String>> transform = name -> name
                .filter(item -> item.length() > stringLength)
                .map(String::toUpperCase)
                .flatMap(this::splitString);
        return Flux.fromIterable(List.of("alex", "ben", "chloe"))
                .transform(transform)
                .defaultIfEmpty("default")
                .log();
    }

    public Flux<String> nameFluxTransformSwitchIfEmpty(int stringLength) {
        Function<Flux<String>,Flux<String>> transform = name -> name
                .filter(item -> item.length() > stringLength)
                .map(String::toUpperCase)
                .flatMap(this::splitString);
        Flux<String> defaultFlux = Flux.just("default")
                .transform(transform);
        return Flux.fromIterable(List.of("alex", "ben", "chloe"))
                .transform(transform)
                .switchIfEmpty(defaultFlux)
                .log();
    }



    public Mono<String> nameMono() {
        return Mono.just("Alex").log();
    }

    public Mono<String> nameMonoMapFilter(int stringLength){
        return Mono.just("alex")
                .map(String::toUpperCase)
                .filter(item -> item.length() > stringLength);
    }

    public Mono<List<String>> nameMonoFlatMap(int stringLength){
        return Mono.just("alex")
                .map(String::toUpperCase)
                .filter(item -> item.length() > stringLength)
                .flatMap(this::splitStringMono);
    }

    //mono -> flux
    public Flux<String> nameMonoFlatMapMany(int stringLength){
        return Mono.just("alex")
                .map(String::toUpperCase)
                .filter(item -> item.length() > stringLength)
                .flatMapMany(this::splitString);
    }

    private Mono<List<String>> splitStringMono(String s) {
        return Mono.just(List.of(s.split("")));
    }

    public Flux<String> concatFlux(){
        Flux<String> abcFlux = Flux.just("A", "B", "C").delayElements(Duration.ofMillis(100));
        Flux<String> defFlux = Flux.just("D", "E", "F").delayElements(Duration.ofMillis(125));
        //one flux finish, then concat the other one
        return Flux.concat(abcFlux, defFlux).log();
    }

    public Flux<String> concatWithFlux(){
        Flux<String> abcFlux = Flux.just("A", "B", "C");
        Flux<String> defFlux = Flux.just("D", "E", "F");
        return abcFlux.concatWith(defFlux).log();
    }

    public Flux<String> concatWithMono(){
        Flux<String> abcFlux = Flux.just("A", "B", "C");
        Mono<String> dMono = Mono.just("D");
        return abcFlux.concatWith(dMono).log();
    }

    public Flux<String> mergeFlux(){
        Flux<String> abcFlux = Flux.just("A", "B", "C").delayElements(Duration.ofMillis(100));
        Flux<String> defFlux = Flux.just("D", "E", "F").delayElements(Duration.ofMillis(125));
        //one flux finish, then concat the other one
        return Flux.merge(abcFlux, defFlux).log();
    }

    public Flux<String> mergeWithFlux(){
        Flux<String> abcFlux = Flux.just("A", "B", "C").delayElements(Duration.ofMillis(100));
        Flux<String> defFlux = Flux.just("D", "E", "F").delayElements(Duration.ofMillis(125));
        //one flux finish, then concat the other one
        return abcFlux.mergeWith(defFlux).log();
    }

    public Flux<String> mergeWithMono(){
        Mono<String> aMono = Mono.just("A");
        Mono<String> dMono = Mono.just("D");
        //one flux finish, then concat the other one
        return aMono.mergeWith(dMono).log();
    }

    public Flux<String> mergeSequentialFlux(){
        Flux<String> abcFlux = Flux.just("A", "B", "C").delayElements(Duration.ofMillis(100));
        Flux<String> defFlux = Flux.just("D", "E", "F").delayElements(Duration.ofMillis(125));
        //one flux finish, then concat the other one
        return Flux.mergeSequential(abcFlux, defFlux).log();
    }

    public Flux<String> zipOperator(){
        Flux<String> abcFlux = Flux.just("A", "B", "C");
        Flux<String> defFlux = Flux.just("D", "E", "F", "G");
        //one flux finish, then concat the other one
        return Flux.zip(abcFlux, defFlux, (a, b) -> a.isEmpty() ? b : a + b).log();
    }




    // AD14, BE25, CF36
    public Flux<String> zipTupleOperator() {

        var abcFlux = Flux.just("A", "B", "C");
        var defFlux = Flux.just("D", "E", "F");
        var flux3 = Flux.just("1", "2", "3");
        var flux4 = Flux.just("4", "5", "6");

        return Flux.zip(abcFlux, defFlux, flux3, flux4)
                .map(t4 -> t4.getT1() + t4.getT2() + t4.getT3() + t4.getT4());


    }


    public Flux<String> monoZip() {
        var aMono = Mono.just("A");
        var bMono = Mono.just("B");
        return Flux.zip(aMono, bMono, (first, second) -> first + second);


    }

    // AD, BE, CF
    public Flux<String> zipWithOperator() {
        var abcFlux = Flux.just("A", "B", "C");
        var defFlux = Flux.just("D", "E", "F");
        return abcFlux.zipWith(defFlux, (first, second) -> first + second);
    }

    public Mono<String> monoZipWithOperator() {
        var aMono = Mono.just("A");
        var bMono = Mono.just("B");
        return aMono.zipWith(bMono)
                .map(t2 -> t2.getT1() + t2.getT2());
    }


    public static void main(String[] args) {
        FluxAndMonoGeneratorService service = new FluxAndMonoGeneratorService();
        service.nameFlux()
                .subscribe(System.out::println);
        service.nameMono()
                .subscribe(System.out::println);
    }
}
