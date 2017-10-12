package com.artshell.reactor.operators;

import reactor.core.publisher.Flux;

import java.util.function.BiFunction;
import java.util.function.Supplier;

public class FluxScan {
    /**
     * @see reactor.core.publisher.Flux#scan(BiFunction)
     */
    private static void scan() {
        Flux.range(1, 100)
                .scan((l, r) -> l + r)
                .subscribe(System.out::println);
        // obtain result:
        // 1, 3, 6, 10, ..., 5050
    }

    /**
     * @see Flux#scan(Object, BiFunction)
     */
    private static void scanInit() {
        Flux.just("a", "b", "c", "d")
                .scan("$", (l, r) -> l + r)
                .subscribe(System.out::println);
        // obtain result:
        // $
        // $a
        // $ab
        // $abc
        // $abcd
    }

    /**
     * @see Flux#scanWith(Supplier, BiFunction)
     */
    private static void scanWith() {
        Flux.just("Q","D","H")
                .scanWith(() -> "#", (l, r) -> l + r)
                .subscribe(System.out::println);

        // obtain result:
        // #
        // #Q
        // #QD
        // #QDH
    }

    public static void main(String[] args) {
//        scan();
//        scanInit();
        scanWith();
    }
}
