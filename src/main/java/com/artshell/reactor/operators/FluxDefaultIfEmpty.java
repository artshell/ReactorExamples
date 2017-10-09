package com.artshell.reactor.operators;

import reactor.core.publisher.Flux;

public class FluxDefaultIfEmpty {
    /**
     * @see Flux#defaultIfEmpty(Object)
     */
    public static void main(String[] args) {
        Flux.<String>empty().defaultIfEmpty("#$").subscribe(System.out::println);

        // obtain result:
        // #$
    }
}
