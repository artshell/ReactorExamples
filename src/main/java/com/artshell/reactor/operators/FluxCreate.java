package com.artshell.reactor.operators;

import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.function.Consumer;

/**
 * @see reactor.core.publisher.Flux#create(Consumer)
 * @see reactor.core.publisher.Flux#create(Consumer, FluxSink.OverflowStrategy)
 */
public class FluxCreate {

    /**
     * @see FluxSink.OverflowStrategy#LATEST
     */
    private static void createBuffer() {
        Flux.create(sink -> {
            Integer[] source = {1, 2, 3, 4, 5, 6};
            for (int i = 0, len = source.length; i < len && !sink.isCancelled(); i++) {
                sink.next(source[i]);
            }
            if (!sink.isCancelled()) {
                sink.complete();
            }
        }, FluxSink.OverflowStrategy.BUFFER).subscribe(System.out::println);
    }

    public static void main(String[] args) {
        createBuffer();
    }
}
