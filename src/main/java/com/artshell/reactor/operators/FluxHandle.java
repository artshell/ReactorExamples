package com.artshell.reactor.operators;

import reactor.core.publisher.Flux;

import java.util.function.BiConsumer;

public class FluxHandle {

    /**
     * @see reactor.core.publisher.Flux#handle(BiConsumer)
     */
    public static void main(String[] args) {
        Flux.range(3, 3)
                .handle((i, sink) -> sink.next(i + "%"))
                .subscribe(System.out::println);
        // obtain result:
        // 3%, 4%, 5%
    }
}
