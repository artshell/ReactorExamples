package com.artshell.reactor.operators;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.function.Predicate;

/**
 * @see reactor.core.publisher.Flux#all(Predicate)
 */
public class FluxAll {
    // same element
    private static Integer[] source = {4, 4, 4, 4, 4};

    // different element
    private static Integer[] source2 = {4, 4, 5, 4, 4};

    // empty element
    private static Integer[] source3 = {};

    public static void main(String[] args) {
        Flux.fromArray(source)
                .all(i -> i == 4)
                .subscribeOn(Schedulers.immediate())
                .publishOn(Schedulers.immediate())
                .subscribe(System.out::println, Throwable::printStackTrace);
        // obtain result:
        // true


        Flux.fromArray(source2)
                .all(i -> i == 5)
                .subscribe(System.out::println, Throwable::printStackTrace);
        // obtain result:
        // false


        Flux.fromArray(source3)
                .all(i -> i == 3)
                .subscribe(System.out::println, Throwable::printStackTrace);
        // obtain result:
        // true
    }
}
