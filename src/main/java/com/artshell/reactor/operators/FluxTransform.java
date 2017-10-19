package com.artshell.reactor.operators;

import reactor.core.publisher.Flux;

import java.util.function.Function;

public class FluxTransform {
    /**
     * @see reactor.core.publisher.Flux#transform(Function)
     */
    public static void main(String[] args) {
        Flux.just("1", "2", "3")
                .transform(f -> f.map(s -> s + "#"))
                .subscribe(System.out::println);

        // obtain result:
        // 1#, 2#, 3#
    }
}
