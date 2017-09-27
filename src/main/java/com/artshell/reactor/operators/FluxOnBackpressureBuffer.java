package com.artshell.reactor.operators;

import reactor.core.publisher.BufferOverflowStrategy;
import reactor.core.publisher.Flux;

import java.util.function.Consumer;

public class FluxOnBackpressureBuffer {

    /**
     * @see Flux#onBackpressureBuffer()  unbounded = true
     * @see Flux#onBackpressureBuffer(int)    unbounded = false
     */
    private static void onBackpressureBuffer() {
        Flux.range(4, 100)
                .doOnRequest(n -> System.out.println("OnRequest => " + n))
                .onBackpressureBuffer()
                .subscribe(System.out::println);

        // obtain result:
        // OnRequest => 9223372036854775807
        // 4, 5, 6, 7, 8, 9 ...
    }

    /**
     * @see Flux#onBackpressureBuffer(int, Consumer)
     * @see Flux#onBackpressureBuffer(int, Consumer, BufferOverflowStrategy)
     */
    private static void onBackpressureBufferConsumer() {

    }

    public static void main(String[] args) {
        onBackpressureBuffer();
    }
}
