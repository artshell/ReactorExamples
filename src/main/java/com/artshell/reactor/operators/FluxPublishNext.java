package com.artshell.reactor.operators;

import reactor.core.publisher.Flux;

/**
 * @see Flux#publishNext()
 */
public class FluxPublishNext {
    public static void main(String[] args) {
        Flux.range(4, 3)
                .publishNext()
                .subscribe(System.out::println);

        // obtain result:
        // 4
    }
}
