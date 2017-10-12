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
        Flux.range(1, 5)
                .sampleTimeout(i -> Flux.<Boolean>empty().delayElements(Duration.ofMillis(50)))
                .subscribe(System.out::println);
        // obtain result:
        // 1,2,3,4,5
    }

    public static void main(String[] args) {
        sampleTimeout();
    }
}
