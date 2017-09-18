package com.artshell.reactor.operators;

import reactor.core.publisher.Flux;

/**
 * @see Flux#count()
 */
public class FluxCount {
    public static void main(String[] args) {
        Flux.range(1, 6)
                .count()
                .subscribe(System.out::println);
        // obtain result:
        // 6
    }
}
