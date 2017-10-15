package com.artshell.reactor.operators;

import reactor.core.publisher.Flux;

public class FluxSingle {
    /**
     * @see Flux#single()
     */
    private static void single() {
        Flux.range(1, 2)
                .single()
                .subscribe(System.out::println);

        // obtain result:
        // java.lang.IndexOutOfBoundsException: Source emitted more than one item
    }

    /**
     * @see Flux#single(Object)
     */
    private static void singleDef() {
        Flux.empty().single("$")
                .subscribe(System.out::println);

        // obtain result:
        // $
    }

    /**
     * @see Flux#singleOrEmpty()
     * @see Flux#single()
     * @see #single()
     */
    private static void singleOr() {
        Flux.empty()
                .singleOrEmpty()
                .subscribe(System.out::println, Throwable::printStackTrace, () -> System.out.println("complete"));
    }


    public static void main(String[] args) {
//        single();
//        singleDef();

        singleOr();
    }
}
