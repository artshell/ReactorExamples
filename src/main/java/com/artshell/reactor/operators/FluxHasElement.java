package com.artshell.reactor.operators;

import reactor.core.publisher.Flux;

public class FluxHasElement {
    /**
     * @see Flux#hasElement(Object)
     */
    private static void hasElement() {
        Flux.range(2, 4)
                .hasElement(5)
                .subscribe(System.out::println);

        // obtain result:
        // true
    }

    /**
     * @see Flux#hasElements()
     */
    private static void hasElements() {
        Flux.empty().hasElements().subscribe(System.out::println);
        // obtain result:
        // false
    }

    public static void main(String[] args) {
//        hasElement();
        hasElements();
    }
}
