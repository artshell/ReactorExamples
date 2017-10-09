package com.artshell.reactor.operators;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;

public class FluxSwitchIfEmpty{
    /**
     * @see Flux#switchIfEmpty(Publisher)
     */
    public static void main(String[] args) {
        Flux.<String>empty().switchIfEmpty(Flux.just("#")).subscribe(System.out::println);

        // obtain result:
        // #
    }
}
