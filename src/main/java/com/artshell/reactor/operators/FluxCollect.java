package com.artshell.reactor.operators;

import reactor.core.publisher.Flux;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * @see reactor.core.publisher.Flux#collect(Collector)
 * @see reactor.core.publisher.Flux#collect(Supplier, BiConsumer)
 */
public class FluxCollect {

    public static void main(String[] args) {
//        collectTypeOne();

//        collectTypeTwo();

        collectSupplier();
    }

    private static void collectTypeOne() {
        Flux.range(1, 10)
                .collect(Collector.of(ArrayList::new, (ArrayList<Integer> array, Integer i) -> array.add(i + 10), /* never used */(left, right) -> left) /*  miss argument Nullable */)
                .subscribe(System.out::println);

        // obtain result:
        // [11, 12, 13, 14, 15, 16, 17, 18, 19, 20]
    }

    private static void collectTypeTwo() {
        Flux.range(1, 10)
                .collect(Collector.of(ArrayList<Integer>::new,
                        (ArrayList<Integer> array, Integer i) -> array.add(i + 5),
                        (left, right) -> left/* never used */,
                        (ArrayList<Integer> source) -> source.subList(0, 5)/* miss argument Nullable */))
                .subscribe(System.out::println);

        // obtain result:
        // [6, 7, 8, 9, 10]
    }

    private static void collectSupplier() {
        Flux.range(1, 5)
                .collect(ArrayList::new, ArrayList::add)
                .subscribe(System.out::println);

        // obtain result:
        // Flux.range(1, 5)
    }
}
