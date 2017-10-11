package com.artshell.reactor.operators;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.function.Function;

public class FluxSampleTimeout {
    /**
     * @see reactor.core.publisher.Flux#sampleTimeout(Function)
     * @see reactor.core.publisher.Flux#sampleTimeout(Function, int)
     */
    private static void sampleTimeout() {
        Flux.range(1, 100)
                .sampleTimeout(i -> Flux.<Boolean>empty().delayElements(Duration.ofMillis(50)))
                .subscribe(System.out::println);
    }

    public static void main(String[] args) {
        sampleTimeout();
    }
}
