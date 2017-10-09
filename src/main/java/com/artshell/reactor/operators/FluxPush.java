package com.artshell.reactor.operators;

import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.function.Consumer;

public class FluxPush {
    /**
     * @see Flux#push(Consumer)
     * @see Flux#push(Consumer, FluxSink.OverflowStrategy)
     *
     * @see Flux#create(Consumer)
     * @see Flux#create(Consumer, FluxSink.OverflowStrategy)
     */
    public static void main(String[] args) {
        Flux.<String>push(emitter -> {
            if (!emitter.isCancelled()) {
                emitter.next("%");
                emitter.complete();
            }
        }).subscribe(System.out::println);
        // obtain result:
        // $
    }
}
