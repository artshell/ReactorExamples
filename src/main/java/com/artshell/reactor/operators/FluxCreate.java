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
            if (sink.isCancelled()) return;
            sink.onRequest(n -> System.out.println("onRequest => " + n));
            Integer[] source = {1, 2, 3, 4, 5, 6};
            for (int i = 0, len = source.length; i < len && !sink.isCancelled(); i++) {
                sink.next(source[i]);
            }
            sink.complete();
        }, FluxSink.OverflowStrategy.BUFFER).subscribe(System.out::println);

        // obtain result:
        // onRequest => 9223372036854775807
        // 1, 2, 3, 4, 5, 6
    }

    public static void main(String[] args) {
        createBuffer();
    }
}
