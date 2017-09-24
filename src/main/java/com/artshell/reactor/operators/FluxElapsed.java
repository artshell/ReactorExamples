package com.artshell.reactor.operators;


import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

/**
 * @see Flux#elapsed()
 * @see Flux#elapsed(Scheduler)
 */
public class FluxElapsed {

    public static void main(String[] args) {
        Flux.range(1, 3)
                .delayElements(Duration.ofMillis(50), Schedulers.newElastic("FluxElapsed"))
                .elapsed()
                .subscribe(t -> {
                    System.out.println("l => " + t.getT1());
                    System.out.println("i > " + t.getT2());
                });

        // obtain result:
        // l => 89
        // i > 1
        // l => 54
        // i > 2
        // l => 52
        // i > 3
    }
}
