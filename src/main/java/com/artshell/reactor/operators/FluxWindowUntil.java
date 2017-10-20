package com.artshell.reactor.operators;

import reactor.core.publisher.Flux;

import java.util.function.Predicate;

public class FluxWindowUntil {
    /**
     * @see reactor.core.publisher.Flux#windowUntil(Predicate)
     * @see reactor.core.publisher.Flux#windowUntil(Predicate, boolean)
     * @see reactor.core.publisher.Flux#windowUntil(Predicate, boolean, int)
     */
    public static void main(String[] args) {
        Flux.concat(Flux.just("A", "BB", "CC", "D", "R"), Flux.just("!", "@@", "#", "$", "%%","^", "&", "**"))
                .windowUntil(s -> s.length() > 2, true, 1)
                .flatMap(flx -> flx)
                .elapsed()
                .subscribe(tuple -> System.out.println("// cost time => " + tuple.getT1() + ", element => " + tuple.getT2()));
        
        // obtain result:
        // cost time => 0, element => A
        // cost time => 0, element => BB
        // cost time => 0, element => CC
        // cost time => 0, element => D
        // cost time => 0, element => R
        // cost time => 0, element => !
        // cost time => 0, element => @@
        // cost time => 0, element => #
        // cost time => 0, element => $
        // cost time => 0, element => %%
        // cost time => 0, element => ^
        // cost time => 0, element => &
        // cost time => 0, element => **
    }
}
