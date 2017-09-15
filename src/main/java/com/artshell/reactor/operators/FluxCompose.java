package com.artshell.reactor.operators;


import reactor.core.publisher.Flux;

import java.util.function.Function;

/**
 * @see reactor.core.publisher.Flux#compose(Function)
 */
public class FluxCompose {

    public static void main(String[] args) {
        Flux.range(1, 5)
                .compose(f -> f.map(i -> "[" + i + "]"))
                .subscribe(System.out::println);
        // obtain result:
        // [1]
        // [2]
        // [3]
        // [4]
        // [5]
    }
}
