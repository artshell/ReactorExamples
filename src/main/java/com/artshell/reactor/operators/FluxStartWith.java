package com.artshell.reactor.operators;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

public class FluxStartWith {
    /**
     * @see reactor.core.publisher.Flux#startWith(Iterable)
     */
    private static void startIter() {
        List<String> array = new ArrayList<>();
        array.add("#");
        array.add("$");
        array.add("*");

        Flux.fromArray(new String[]{"A", "B", "C"})
                .startWith(array)
                .subscribe(System.out::println);

        // obtain result:
        // #, $, *, A, B, C
    }

    /**
     * @see Flux#startWith(Object[])
     */
    private static void startInit() {
        Flux.just("%")
                .startWith("#")
                .subscribe(System.out::println);

        // obtain result:
        // #, %
    }

    /**
     * @see Flux#startWith(Publisher)
     */
    private static void startPub() {
        Flux.just(1)
                .startWith(Flux.just(5))
                .subscribe(System.out::println);

        // obtain result:
        // 5, 1
    }

    public static void main(String[] args) {
//        startIter();
//        startInit();
        startPub();
    }
}
