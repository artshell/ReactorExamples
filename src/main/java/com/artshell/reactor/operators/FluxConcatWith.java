package com.artshell.reactor.operators;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;

/**
 * @see reactor.core.publisher.Flux#concatWith(Publisher)
 */
public class FluxConcatWith {
    public static void main(String[] args) {
        Integer[] iter = {1, 2, 3, 4};
        Flux.fromArray(iter)
                .concatWith(Flux.just(5))
                .subscribe(System.out::println);

        // obtain result:
        // 1
        // 2
        // 3
        // 4
        // 5
    }
}
