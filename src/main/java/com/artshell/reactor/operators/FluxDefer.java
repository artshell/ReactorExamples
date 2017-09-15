package com.artshell.reactor.operators;


import reactor.core.publisher.Flux;

import java.util.function.Supplier;

/**
 * @see reactor.core.publisher.Flux#defer(Supplier)
 */
public class FluxDefer {
    private static Integer[] hotSource = {1, 5, 8 ,7};

    public static void main(String[] args) {
        Flux<Integer> defer = Flux.defer(() -> Flux.fromArray(hotSource));

        defer.subscribe(System.out::println);
        // obtain result:
        // 1
        // 5
        // 8
        // 7

        hotSource[1] = 3;
        defer.subscribe(System.out::println);
        // obtain result:
        // 1
        // 3
        // 8
        // 7
    }
}
