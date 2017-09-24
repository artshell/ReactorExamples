package com.artshell.reactor.operators;

import reactor.core.publisher.Flux;

import java.util.function.Function;

public class FluxFilterWhen {

    /**
     * @see reactor.core.publisher.Flux#filterWhen(Function)
     * @see reactor.core.publisher.Flux#filterWhen(Function, int)
     * @param args
     */
    public static void main(String[] args) {
        Flux.just("a", "b", "e", "f")
                .filterWhen(i -> Flux.just("e".equals(i)), 2)
                .subscribe(System.out::println);

        // obtain result:
        // e
    }
}
