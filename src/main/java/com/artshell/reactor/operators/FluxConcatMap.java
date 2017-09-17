package com.artshell.reactor.operators;


import reactor.core.publisher.Flux;

import java.util.function.Function;

/**
 * @see reactor.core.publisher.Flux#concatMap(Function)
 */
public class FluxConcatMap {

    public static void main(String[] args) {
        Flux.just(1)
                .concatMap(i -> Flux.just("java"))
                .subscribe(System.out::println);

        // obtain result:
        // java
    }
}
