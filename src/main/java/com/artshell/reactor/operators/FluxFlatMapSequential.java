package com.artshell.reactor.operators;

import reactor.core.publisher.Flux;

import java.util.function.Function;

/**
 * @see reactor.core.publisher.Flux#flatMapSequential(Function)
 */
public class FluxFlatMapSequential {
    public static void main(String[] args) {
        Flux.range(1, 3)
                .flatMapSequential(i -> Flux.just(i + "#"))
                .subscribe(System.out::println);
        // obtain result:
        // 1#
        // 2#
        // 3#
    }
}
