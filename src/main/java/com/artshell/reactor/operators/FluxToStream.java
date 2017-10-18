package com.artshell.reactor.operators;

import reactor.core.publisher.Flux;

public class FluxToStream {
    /**
     * @see Flux#toStream()
     * @see Flux#toStream(int)
     */
    public static void main(String[] args) {
        Integer[] array = Flux.range(1, 10)
                .toStream(5)
                .toArray(Integer[]::new);

        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }

        // obtain result:
        // 1, 2, 3, 4, 5, 6, 7, 8, 9, 10
    }
}
