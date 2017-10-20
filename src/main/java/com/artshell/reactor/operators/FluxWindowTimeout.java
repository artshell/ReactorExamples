package com.artshell.reactor.operators;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;

import java.time.Duration;

public class FluxWindowTimeout {
    /**
     * @param args
     * @see reactor.core.publisher.Flux#windowTimeout(int, Duration)
     * @see reactor.core.publisher.Flux#windowTimeout(int, Duration, Scheduler)
     */
    public static void main(String[] args) {
        Flux.concat(
                Flux.just("#").delayElements(Duration.ofMillis(20)),
                Flux.range(1, 10),
                Flux.range(11, 5).delayElements(Duration.ofMillis(15))
        )
                .windowTimeout(10, Duration.ofMillis(1))
                .flatMap(flx -> flx)
                .subscribe(System.out::println);

        // obtain result：
        // [ERROR] (parallel-1) Scheduler worker in group main failed with an uncaught exception - reactor.core.Exceptions$ErrorCallbackNotImplemented: java.lang.IllegalStateException: UnicastProcessor allows only a single Subscriber
        // reactor.core.Exceptions$ErrorCallbackNotImplemented: java.lang.IllegalStateException: UnicastProcessor allows only a single Subscriber
        // Caused by: java.lang.IllegalStateException: UnicastProcessor allows only a single Subscriber
    }
}
