package com.artshell.reactor.operators;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;

public class FluxThenMany {
    /**
     * @see reactor.core.publisher.Flux#thenMany(Publisher)
     */
    public static void main(String[] args) {
        Flux.just("$")
                .thenMany(Flux.just("#"))
                .subscribe(System.out::println);

        // obtain result:
        // #
    }
}
