package com.artshell.reactor.operators;


import reactor.core.publisher.Flux;

public class FluxDistinct {

    /**
     * @see Flux#distinct()
     */
    private static void distinct() {
        Flux.just(1, 3, 4, 3, 1, 6)
                .distinct()
                .subscribe(System.out::println);

        // obtain result:
        // 1, 3, 4, 6
    }

    private static void distinctFun() {
        Flux.just(2, 5, 2, 8, 7, 5)
                .distinct(i -> i + "$")
                .subscribe(System.out::println);
        // obtain result:
        // 2, 5, 8, 7
    }

    public static void main(String[] args) {
//        distinct();
        distinctFun();
    }
}
