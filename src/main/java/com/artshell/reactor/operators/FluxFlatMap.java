package com.artshell.reactor.operators;

import reactor.core.publisher.Flux;

import java.util.function.Function;
import java.util.function.Supplier;

public class FluxFlatMap {

    /**
     * @see reactor.core.publisher.Flux#flatMap(Function, Function, Supplier)
     */
    private static void flatMap() {
        Flux.just("rust")
                .flatMap(r -> Flux.just(r + "*"), thr -> Flux.error(new RuntimeException(thr)), () -> Flux.just("##"))
                .subscribe(System.out::println, Throwable::printStackTrace, () -> {});

        // obtain result:
        // rust*
        // #
    }

    public static void main(String[] args) {
        flatMap();
    }
}
