package com.artshell.reactor.operators;

import reactor.core.publisher.Flux;

import java.util.ArrayDeque;
import java.util.function.Supplier;

public class FluxToIterable {

    /**
     * @see Flux#toIterable()
     * @see Flux#toIterable(int)
     * @see Flux#toIterable(int, Supplier)
     */
    public static void main(String[] args) {
        Iterable<Integer> iter = Flux.range(1, 10).toIterable(5, ArrayDeque::new);
        iter.forEach(System.out::println);

        // obtain result:
        // 1, 2, 3, 4, 5, 6, 7, 8, 9, 10
    }
}
