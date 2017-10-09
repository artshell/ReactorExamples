package com.artshell.reactor.operators;

import reactor.core.publisher.Flux;

import java.util.function.BiFunction;
import java.util.function.Supplier;

public class FluxReduce {
    /**
     * @see reactor.core.publisher.Flux#reduce(BiFunction)
     */
    private static void reduceBiFun() {
        Flux.range(2, 5)
                .reduce((l, r) -> l + r)
                .subscribe(System.out::println);
        // obtain result:
        // 20
    }

    /**
     * @see Flux#reduce(Object, BiFunction)
     */
    private static void reduceInit() {
        Flux.range(1, 3)
                .reduce("$", (l, r) -> l + r)
                .subscribe(System.out::println);
        // obtain result:
        // $123
    }

    /**
     * @see Flux#reduceWith(Supplier, BiFunction)
     */
    private static void reduceWith() {
        Flux.range(2, 3)
                .reduceWith(System::currentTimeMillis, (l, r) -> l + r)
                .subscribe(System.out::println);

        // obtain result:
        // 1507541283821
    }

    public static void main(String[] args) {
//        reduceBiFun();
//        reduceInit();
        reduceWith();
    }
}
