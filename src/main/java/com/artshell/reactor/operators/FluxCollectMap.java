package com.artshell.reactor.operators;

import reactor.core.publisher.Flux;

import java.util.TreeMap;
import java.util.function.Function;
import java.util.function.Supplier;

public class FluxCollectMap {

    /**
     * @see reactor.core.publisher.Flux#collectMap(Function)
     */
    private static void oneArgument() {
        Flux.range(1, 5)
                .collectMap(v -> "(" + v +")")
                .subscribe(System.out::println);

        // obtain result:
        // {(1)=1, (5)=5, (4)=4, (3)=3, (2)=2}
    }


    /**
     * @see reactor.core.publisher.Flux#collectMap(Function, Function)
     */
    private static void twoArgument() {
        Flux.range(1, 5)
                .collectMap(v -> "(" + v +")", v -> v + 10)
                .subscribe(System.out::println);

        // obtain result:
        // {(1)=11, (5)=15, (4)=14, (3)=13, (2)=12}
    }

    /**
     * @see reactor.core.publisher.Flux#collectMap(Function, Function, Supplier)
     */
    private static void threeArgument() {
        Flux.range(1, 5)
                .collectMap(v -> "(" + v +")", v -> v + 10, TreeMap::new)
                .subscribe(System.out::println);

        // obtain result:
        // {(1)=11, (2)=12, (3)=13, (4)=14, (5)=15}
    }


    public static void main(String[] args) {
//        oneArgument();
//        twoArgument();
        threeArgument();
    }
}
