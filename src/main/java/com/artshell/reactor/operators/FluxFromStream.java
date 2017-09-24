package com.artshell.reactor.operators;

import reactor.core.publisher.Flux;

import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * @see reactor.core.publisher.Flux#fromStream(Stream)
 */
public class FluxFromStream {
    /**
     * @see java.util.Spliterators.ArraySpliterator#forEachRemaining(Consumer)
     * @param args
     */
    public static void main(String[] args) {
        Flux.fromStream(Stream.of("java", "rust", "e", "f"))
                .subscribe(System.out::println);
        // obtain result:
        // java
        // rust
        // e
        // f
    }
}
