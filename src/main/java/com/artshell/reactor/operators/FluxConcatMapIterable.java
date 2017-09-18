package com.artshell.reactor.operators;


import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * @see reactor.core.publisher.Flux#concatMapIterable(Function)
 */
public class FluxConcatMapIterable {
    public static void main(String[] args) {
        Flux.range(1, 5)
                .concatMapIterable(i -> {
                    List<String> iter = new ArrayList<>();
                    iter.add(i + "#");
                    return iter;
                })
                .subscribe(System.out::println, Throwable::printStackTrace);

        // obtain result:
        // 1#
        // 2#
        // 3#
        // 4#
        // 5#
    }
}
