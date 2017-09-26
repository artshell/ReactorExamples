package com.artshell.reactor.operators;

import reactor.core.publisher.Flux;

public class FluxNext{
    /**
     * @see Flux#next()
     */
    public static void main(String[] args) {
        // Emit only the first item
        Flux.just(1, 2, 3)
                .next()
                .doOnSuccess(System.out::println)
                .subscribe();

        // obtain result:
        // 1
    }
}
