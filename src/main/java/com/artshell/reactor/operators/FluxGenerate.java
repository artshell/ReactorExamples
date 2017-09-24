package com.artshell.reactor.operators;

import reactor.core.publisher.Flux;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;
import java.util.function.Consumer;

public class FluxGenerate {
    /**
     * @see Flux#generate(Callable, BiFunction)
     */
    private static void generateCall() {
        Flux.generate(() -> 0, (i, sink) -> {
            sink.next(i + "$");
            if (i == 10) {
                sink.complete();
            }
            return ++i;
        }).subscribe(System.out::println);

        // obtain result:
        // 0$
        // 1$
        // 2$
        // 3$
        // 4$
        // 5$
        // 6$
        // 7$
        // 8$
        // 9$
        // 10$
    }

    /**
     * @see Flux#generate(Callable, BiFunction, Consumer)
     */
    private static void generateCallCons() {
        Flux.generate(AtomicInteger::new, (i, sink) -> {
            if (i.getAndIncrement() < 5) {
                sink.next(i.get() + "#");
            } else {
                sink.complete();
            }
            return i;
        }, i -> {
            i.set(0);
            System.out.println("consumer state");
        }).subscribe(System.out::println);

        // obtain result:
        // 1#
        // 2#
        // 3#
        // 4#
        // 5#
        // consumer state
    }

    /**
     * @see Flux#generate(Consumer)
     */
    private static void generateCons() {
        Flux.generate(sink -> {
            sink.next("$$$");
            sink.complete();
        }).subscribe(System.out::println);
        // obtain result:
        // $$$
    }

    public static void main(String[] args) {
//        generateCall();
//        generateCallCons();
        generateCons();
    }
}
