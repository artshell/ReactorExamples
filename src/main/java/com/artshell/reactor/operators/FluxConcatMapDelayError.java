package com.artshell.reactor.operators;


import reactor.core.publisher.Flux;

import java.util.function.Function;

public class FluxConcatMapDelayError {

    /**
     * @see reactor.core.publisher.Flux#concatMapDelayError(Function)
     * @see reactor.core.publisher.Flux#concatMapDelayError(Function, int)
     */
    private static void mapDelayErrorBoundary() {

        Flux.just(1, 2, 0, 5, 0)
                .concatMapDelayError(i -> Flux.just(100 / i))
                .subscribe(System.out::println, Throwable::printStackTrace);
        // obtain result:
        // 100
        // 50
        // java.lang.ArithmeticException: / by zero

    }

    /**
     * @see reactor.core.publisher.Flux#concatMapDelayError(Function, boolean, int)
     */
    private static void mapDelayError() {

        Flux.just(1, 2, 0, 5, 0)
                .concatMapDelayError(i -> Flux.just(100 / i), true, 10)
                .subscribe(System.out::println, Throwable::printStackTrace);
    }

    public static void main(String[] args) {
//        mapDelayErrorBoundary();
        mapDelayError();

    }

}
