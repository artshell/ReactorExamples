package com.artshell.reactor.operators;


import reactor.core.publisher.Flux;

/**
 * @see reactor.core.publisher.Flux#elementAt(int)
 * @see reactor.core.publisher.Flux#elementAt(int, Object)
 */
public class FluxElementAt {

    public static void main(String[] args) {
        Flux.range(1, 3)
                .elementAt(0)
                .subscribe(System.out::println);

        // obtain result:
        // 1
    }
}
