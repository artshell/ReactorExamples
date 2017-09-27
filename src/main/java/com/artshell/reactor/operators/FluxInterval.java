package com.artshell.reactor.operators;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

public class FluxInterval {

    /**
     * @see reactor.core.publisher.Flux#interval(Duration)
     * @see reactor.core.publisher.Flux#interval(Duration, Scheduler)
     */
    private static void interval() {
        Flux.interval(Duration.ofMillis(100), Schedulers.newElastic("interval"))
                .concatMap(l -> Flux.just(l + "$"))
                .elapsed()
                .subscribe(tuple -> {
                    System.out.println("cost time => " + tuple.getT1());
                    System.out.println("element => " + tuple.getT2());
                });

        // obtain result:
        // cost time => 110
        // element => 0$
        // cost time => 109
        // element => 1$
        // cost time => 94
        // element => 2$
        // cost time => 109
        // element => 3$
        // cost time => 94
        // element => 4$
        // ...
    }

    /**
     * @see reactor.core.publisher.Flux#interval(Duration, Duration)
     * @see reactor.core.publisher.Flux#interval(Duration, Duration, Scheduler)
     */
    private static void intervalDelay() {
        Flux.interval(Duration.ofMillis(50), Duration.ofMillis(100), Schedulers.newSingle("intervalDelay"))
                .switchMap(l -> Flux.just(l + "#"))
                .elapsed()
                .subscribe(tuple -> {
                    System.out.println("cost time => " + tuple.getT1());
                    System.out.println("element => " + tuple.getT2());
                });

        // obtain result:
        // cost time => 63
        // element => 0#
        // cost time => 109
        // element => 1#
        // cost time => 94
        // element => 2#
        // cost time => 94
        // element => 3#
        // ...
    }

    public static void main(String[] args) {
//        interval();
        intervalDelay();
    }
}
