package com.artshell.reactor.operators;

import reactor.core.publisher.Flux;

public class FluxLast {

    /**
     * @see Flux#last()
     */
    private static void last() {
        Flux.just("a", "b", "c", "d")
                .last()
                .subscribe(System.out::println);

        // obtain result:
        // d
    }

    /**
     * @see Flux#last(Object)
     */
    private static void lastDefault() {
        Flux.empty().last(9).subscribe(System.out::println);

        // obtain result:
        // 9
    }

    public static void main(String[] args) {
//        last();
        lastDefault();
    }
}
