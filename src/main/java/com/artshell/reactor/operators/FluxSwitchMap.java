package com.artshell.reactor.operators;

import reactor.core.publisher.Flux;

import java.util.function.Function;

public class FluxSwitchMap {
    /**
     * @see reactor.core.publisher.Flux#switchMap(Function)
     * @see reactor.core.publisher.Flux#switchMap(Function, int)
     */
    public static void main(String[] args) {
        Flux.range(4, 3)
                .switchMap(i -> Flux.just(i + "$"))
                .subscribe(System.out::println);
        // obtain result:
        // 4$, 5$, 6$
    }
}
