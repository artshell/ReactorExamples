package com.artshell.reactor.operators;

import reactor.core.publisher.Flux;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

public class FluxCollectMultimap {

    /**
     * @see reactor.core.publisher.Flux#collectMultimap(Function)
     */
    private static void oneArgument() {
        Flux.range(1, 5)
                .collectMultimap(i -> "(" + i + ")")
                .subscribe(System.out::println);

        // obtain result:
        // {(1)=[1], (5)=[5], (4)=[4], (3)=[3], (2)=[2]}
    }

    /**
     * @see reactor.core.publisher.Flux#collectMultimap(Function, Function)
     */
    private static void twoArgument() {
        Flux.range(1, 5)
                .collectMultimap(i -> "(" + i + ")", v -> v + "$")
                .subscribe(System.out::println);

        // obtain result:
        // {(1)=[1$], (5)=[5$], (4)=[4$], (3)=[3$], (2)=[2$]}
    }

    /**
     * @see reactor.core.publisher.Flux#collectMultimap(Function, Function, Supplier)
     */
    private static void threeArgument() {
        Flux.range(1, 5)
                .collectMultimap(i -> "(" + i + ")", v -> v + "#", () -> new TreeMap<>())
                .subscribe(System.out::println);

        // obtain result:
        // {(1)=[1#], (2)=[2#], (3)=[3#], (4)=[4#], (5)=[5#]}
    }

    public static void main(String[] args) {
//        oneArgument();
//        twoArgument();
        threeArgument();
    }
}
