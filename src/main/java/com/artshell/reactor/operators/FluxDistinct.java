package com.artshell.reactor.operators;


import reactor.core.publisher.Flux;

import java.util.TreeSet;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Supplier;

public class FluxDistinct {

    /**
     * @see Flux#distinct()
     */
    private static void distinct() {
        Flux.just(1, 3, 4, 3, 1, 6)
                .distinct()
                .subscribe(System.out::println);

        // obtain result:
        // 1, 3, 4, 6
    }

    /**
     * @see Flux#distinct(Function)
     */
    private static void distinctFun() {
        Flux.just(2, 5, 2, 8, 7, 5)
                .distinct(i -> i + "$")
                .subscribe(System.out::println);
        // obtain result:
        // 2, 5, 8, 7
    }

    /**
     * @see Flux#distinct(Function, Supplier)
     */
    private static void distinctSupplier() {
        Flux.just(4,2,6,7,2,6)
                .distinct(i -> i + "#", TreeSet::new)
                .subscribe(System.out::println);
        // obtain result:
        // 4, 2, 6, 7
    }

    /**
     * @see Flux#distinctUntilChanged()
     * @see Flux#distinctUntilChanged(Function)
     */
    private static void distinctUntilChange() {
        Flux.just(3, 3, 3, 5, 1, 6)
                .distinctUntilChanged(Object::toString)
                .subscribe(System.out::println);
        // obtain result:
        // 3, 5, 1, 6
    }

    /**
     * @see Flux#distinctUntilChanged(Function, BiPredicate)
     */
    private static void distinctUntilChangeKey() {
        Flux.just(7, 5, 5, 5, 1, 1)
                .distinctUntilChanged(i -> i + "#", Object::equals)
                .subscribe(System.out::println);
        // obtain result:
        // 7, 5, 1
    }


    public static void main(String[] args) {
//        distinct();
//        distinctFun();
//        distinctSupplier();
//        distinctUntilChange();
        distinctUntilChangeKey();
    }
}
