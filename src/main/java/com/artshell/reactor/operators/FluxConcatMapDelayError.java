package com.artshell.reactor.operators;


import reactor.core.publisher.Flux;

import java.util.function.Function;

public class FluxConcatMapDelayError {

    /**
     * @see reactor.core.publisher.Flux#concatMapDelayError(Function)
     * @see reactor.core.publisher.Flux#concatMapDelayError(Function, int)
     */
    private static void mapDelayErrorBoundary() {
        Flux<Integer> errorElement = Flux.just(1).flatMap(i -> {
            if (i > 1) {
                return Flux.just(i);
            } else {
                return Flux.error(new IllegalStateException());
            }
        }).doOnSubscribe(s -> System.out.println("error Element => doOnSubscribe"));

        Flux.concat(Flux.just(3), Flux.just(9),errorElement, Flux.just(6))
                .concatMapDelayError(i -> Flux.just(i + "#"))
                .subscribe(System.out::println, Throwable::printStackTrace);
        // obtain result:
        // 3#
        // 9#
        // error Element => doOnSubscribe
        // java.lang.IllegalStateException
    }

    /**
     * @see reactor.core.publisher.Flux#concatMapDelayError(Function, boolean, int)
     */
    private static void mapDelayErrorEnd() {
        Flux.concat(Flux.just(3), Flux.just(9),Flux.just(6))
                .concatMapDelayError(i -> {
                    if (i == 9) {
                        return Flux.error(new IllegalStateException());
                    }
                    return Flux.just(i + "#");
                }, true, 10)
                .subscribe(System.out::println, Throwable::printStackTrace);

        // obtain result:
        // 3#
        // java.lang.IllegalStateException
    }

    /**
     * @see Flux#concatMapDelayError(Function, boolean, int)
     */
    private static void mapDelayErrorBoundary2() {
        Flux.concat(Flux.just(2), Flux.just(7),Flux.just(5))
                .concatMapDelayError(i -> {
                    if (i == 7) {
                        return Flux.error(new IllegalStateException());
                    }
                    return Flux.just(i + "#");
                }, false, 10)
                .subscribe(System.out::println, Throwable::printStackTrace);
        // obtain result:
        // 2#
        // java.lang.IllegalStateException
    }


    public static void main(String[] args) {
//        mapDelayErrorBoundary();
//        mapDelayErrorEnd();
        mapDelayErrorBoundary2();
    }
}
