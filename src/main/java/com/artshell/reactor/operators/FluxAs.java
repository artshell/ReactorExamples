package com.artshell.reactor.operators;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

/**
 * @see reactor.core.publisher.Flux#as(Function)
 *
 */
public class FluxAs {

    /**
     * here example:
     * that the as operator obtains Mono<T> type source
     *
     * @param args
     */
    public static void main(String[] args) {
        Flux.just("java")
                .as(Mono::from)
                .subscribe(System.out::println);
    }
}
