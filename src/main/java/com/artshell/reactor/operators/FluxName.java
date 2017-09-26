package com.artshell.reactor.operators;

import reactor.core.Scannable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxOperator;

public class FluxName {
    public static void main(String[] args) {
        FluxOperator<String, String> name = (FluxOperator<String, String>)Flux.just("B").name("Flux-Name");
        name.subscribe(System.out::println);
        System.out.println(name.scanUnsafe(Scannable.Attr.NAME));

        // obtain result
        // B
        // Flux-Name
    }
}
