package com.artshell.reactor.operators;

import reactor.core.publisher.Flux;

import java.util.Comparator;

public class FluxSort {
    /**
     * @see Flux#sort()
     */
    private static void sort() {
        Flux.fromArray(new String[]{"2", "$", "#", "B", "c", "!"})
                .sort()
                .subscribe(System.out::println);

        // obtain result:
        // !, #, $, 2, B, c
    }

    /**
     * @see Flux#sort(Comparator)
     */
    private static void sortComparator() {
        Flux.fromArray(new String[]{"k", "$", "#", "E", "%", "]"})
                .sort(String::compareTo)
                .subscribe(System.out::println);

        // obtain result:
        // #, $, %, E, ], k
    }

    public static void main(String[] args) {
//        sort();
        sortComparator();
    }
}
