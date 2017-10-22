package com.artshell.reactor.operators;

import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.function.BiFunction;

public class FluxZipWithIterable {
    /**
     * @see reactor.core.publisher.Flux#zipWithIterable(Iterable)
     * @see reactor.core.publisher.Flux#zipWithIterable(Iterable, BiFunction)
     */
    public static void main(String[] args) {
        Flux.range(4 , 5)
                .zipWithIterable(Arrays.asList("#", "$", "&", "%", "@", "*"), (l, r) -> l + r)
                .subscribe(System.out::println);
        // obtain result:
        // 4#
        // 5$
        // 6&
        // 7%
        // 8@
        // last element be ignored
    }
}
