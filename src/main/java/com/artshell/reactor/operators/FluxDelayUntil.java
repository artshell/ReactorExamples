package com.artshell.reactor.operators;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.function.Function;

/**
 * @see reactor.core.publisher.Flux#delayUntil(Function)
 */
public class FluxDelayUntil {
    public static void main(String[] args) {
        Flux.just(1,2,3)
                .delayUntil(i -> Flux.just(i).delayElements(Duration.ofMillis(500), Schedulers.newSingle("FluxDelayUntil")))
                .subscribe(System.out::println);

        // each element delay 500 mill obtain result:
        // 1
        // 2
        // 3
    }
}
