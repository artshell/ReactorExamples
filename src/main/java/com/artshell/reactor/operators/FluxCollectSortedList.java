package com.artshell.reactor.operators;


import reactor.core.publisher.Flux;

import java.util.Comparator;

/**
 * @see Flux#collectSortedList()
 * @see Flux#collectSortedList(Comparator)
 */
public class FluxCollectSortedList {

    public static void main(String[] args) {
        Flux.just("a", "W", "1", "9", "b", "5", "3")
                .collectSortedList()
                .subscribe(System.out::println);
        // obtain result:
        // [1, 3, 5, 9, W, a, b]
    }
}
