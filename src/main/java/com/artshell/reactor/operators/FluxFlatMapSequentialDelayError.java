package com.artshell.reactor.operators;

import reactor.core.publisher.Flux;

import java.util.function.Function;

/**
 * @see reactor.core.publisher.Flux#flatMapSequentialDelayError(Function, int, int)
 */
public class FluxFlatMapSequentialDelayError {
    public static void main(String[] args) {
        Flux.range(4, 3)
                .flatMapSequentialDelayError(i -> {
                    if (i == 5) {
                        return Flux.error(new IllegalStateException());
                    }
                    return Flux.just(i + "#");
                }, 1, 2)
                .subscribe(System.out::println);
        // obtain result:
        // 4#
        // 6#
        // Exception in thread "main" reactor.core.Exceptions$ErrorCallbackNotImplemented: java.lang.IllegalStateException
        // Caused by: java.lang.IllegalStateException
    }
}
