package com.artshell.reactor.operators;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;

import java.util.function.BiFunction;

public class FluxZipWith {
    /**
     * @see reactor.core.publisher.Flux#zipWith(Publisher)
     * @see reactor.core.publisher.Flux#zipWith(Publisher, BiFunction)
     */
    private static void zipWithFun() {
        Flux.range(1 , 5)
                .zipWith(Flux.just("#", "$", "&", "%", "@", "*"), (l, r) -> l + r)
                .subscribe(System.out::println);

        // obtain result:
        // 1#
        // 2$
        // 3&
        // 4%
        // 5@
        // last element be ignored
    }

    /**
     * @see reactor.core.publisher.Flux#zipWith(Publisher, int)
     * @see reactor.core.publisher.Flux#zipWith(Publisher, int, BiFunction)
     */
    private static void zipWithBiFun() {
        Flux.range(1 , 5)
                .zipWith(Flux.just("#", "$", "&", "%", "@", "*"), 2, (l, r) -> l + r)
                .subscribe(System.out::println);
        // obtain result:
        // 1#
        // 2$
        // 3&
        // 4%
        // 5@
        // last element be ignored
    }

    public static void main(String[] args) {
//        zipWithFun();
        zipWithBiFun();
    }
}
