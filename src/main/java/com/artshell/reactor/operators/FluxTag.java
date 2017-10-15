package com.artshell.reactor.operators;

import reactor.core.Scannable;
import reactor.core.publisher.Flux;

public class FluxTag {
    /**
     * @see reactor.core.publisher.Flux#tag(String, String)
     */
    public static void main(String[] args) {
        Flux<String> tag = Flux.just("#").tag("fluxTag", "this reactor");
        tag.subscribe(System.out::println);

        // obtain result:
        // #

        Scannable s = (Scannable) tag;
        s.tags().forEach(tuple -> {
            System.out.println("key => " + tuple.getT1());
            System.out.println("value => " + tuple.getT2());
        });

        // obtain result:
        // key => fluxTag
        // value => this reactor
    }
}
