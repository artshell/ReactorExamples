package com.artshell.reactor.operators;

import reactor.core.publisher.Flux;

import java.util.function.Function;

/**
 * @see reactor.core.publisher.Flux#flatMapDelayError(Function, int, int)
 */
public class FluxFlatMapDelayError {
    public static void main(String[] args) {
        Flux.just("a", "b", "c")
                .flatMapDelayError(s -> {
                    if ("b".equals(s)) {
                        return Flux.error(new IllegalStateException());
                    }
                    return Flux.just(s);
                }, 1, 5)
                .subscribe(System.out::println, Throwable::printStackTrace);

        // obtain result:
        // a
        // java.lang.IllegalStateException
    }
}
