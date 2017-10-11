package com.artshell.reactor.operators;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.function.Function;

public class FluxSampleFirst {
    /**
     * @see reactor.core.publisher.Flux#sampleFirst(Duration)
     */
    private static void sampleFirstDur() {
        Flux.range(1, 100)
                .sampleFirst(Duration.ofMillis(50))
                .subscribe(System.out::println);

        // obtain result:
        // 1
    }

    /**
     * @see Flux#sampleFirst(Function)
     */
    private static void sampleFirstFun() {
        Flux.range(1, 10000)
                .sampleFirst(i -> i == 3 ? Flux.just(true).delayElements(Duration.ZERO) : Flux.empty())
                .subscribe(System.out::println);
        // obtain result:
        // 1, 2, 3
    }

    public static void main(String[] args) {
//        sampleFirstDur();
        sampleFirstFun();
    }
}
