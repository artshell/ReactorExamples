package com.artshell.reactor.operators;

import reactor.core.publisher.Flux;

public class FluxLimitRate {

    /**
     * @see Flux#limitRate(int)
     */
    public static void main(String[] args) {
        Flux.range(1, 100)
                .limitRate(5)
                .subscribe(System.out::println);

        // obtain result:
        // 1, 2, 3, 4, 5, ...
    }
}
