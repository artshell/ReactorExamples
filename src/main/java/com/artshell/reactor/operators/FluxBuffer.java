package com.artshell.reactor.operators;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;


import java.time.Duration;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @see reactor.core.publisher.Flux#buffer(int, int)
 * @see reactor.core.publisher.Flux#buffer(Publisher)
 * @see reactor.core.publisher.Flux#buffer(Duration, Duration, Scheduler)
 * @see reactor.core.publisher.Flux#bufferUntil(Predicate, boolean)
 * @see reactor.core.publisher.Flux#bufferWhen(Publisher, Function)
 */
public class FluxBuffer {
    public static void main(String[] args) {
//        bufferMaxSizeSkip();
//        bufferMaxSizeEquals();
        bufferCompanion();
    }

    public static void bufferMaxSizeSkip() {
        Flux.range(1, 100)
                .buffer(5, 3)
                .subscribe(System.out::println);
        // obtain result:
        // [1, 2, 3, 4, 5]
        // [4, 5, 6, 7, 8]
        // [7, 8, 9, 10, 11]
        // ...
    }

    public static void bufferMaxSizeEquals() {
        Flux.range(1, 100)
                .buffer(5, 5)
                .subscribe(System.out::println);
        // obtain result:
        // [1, 2, 3, 4, 5]
        // [6, 7, 8, 9, 10]
        // [11, 12, 13, 14, 15]
        // ...
    }

    public static void bufferCompanion() {
        Flux.range(1, 20000)
                .delayElements(Duration.ofMillis(100))
                .buffer(Flux.range(1, 5).delayElements(Duration.ofMillis(300), Schedulers.newElastic("inner")))
                .publishOn(Schedulers.immediate())
                .subscribe(System.out::println);

        // obtain result:
        // [1, 2]
        // [3, 4, 5]
        // [6, 7, 8]
        // [9, 10, 11]
        // [12, 13, 14]
    }
}
