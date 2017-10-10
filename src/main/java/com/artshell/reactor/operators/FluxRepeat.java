package com.artshell.reactor.operators;

import reactor.core.publisher.Flux;

import java.util.function.BooleanSupplier;
import java.util.function.Function;

public class FluxRepeat {
    /**
     * @see Flux#repeat()
     * @see Flux#repeat(BooleanSupplier)
     */
    private static void repeat() {
        Flux.just("c", "d", "f")
                .repeat(() -> true)
                .subscribe(System.out::println);
        // obtain result:
        // c,d,f  c,d,f  c,d,f ...
    }

    /**
     * @see Flux#repeat(long)
     * @see Flux#repeat(long, BooleanSupplier)
     */
    private static void repeatCount() {
        Flux.just("#")
                .repeat(2, () -> true)
                .subscribe(System.out::println);
        // obtain result:
        // #, #, #
    }

    /**
     * @see Flux#repeatWhen(Function)
     */
    private static void repeatWhen() {
        Flux.just("%")
                .repeatWhen(fun -> {
                    System.out.println("turn on => " + System.currentTimeMillis());
                    return Flux.just("notify");
                })
                .subscribe(System.out::println);
        // obtain result
        // turn on => 1507623353865
        // %, %
    }

    public static void main(String[] args) {
//        repeat();
//        repeatCount();
        repeatWhen();
    }
}
