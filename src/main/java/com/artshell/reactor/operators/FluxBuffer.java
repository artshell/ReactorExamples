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
 */
public class FluxBuffer {
    public static void main(String[] args) {
//        bufferMaxSizeSkip();
//        bufferMaxSizeEquals();
//        bufferCompanion();
//        bufferDuration();
    }

    private static void bufferMaxSizeSkip() {
        Flux.range(1, 100)
                .buffer(5, 3)
                .subscribe(System.out::println);
        // obtain result:
        // [1, 2, 3, 4, 5]
        // [4, 5, 6, 7, 8]
        // [7, 8, 9, 10, 11]
        // ...
    }

    private static void bufferMaxSizeEquals() {
        Flux.range(1, 100)
                .buffer(5, 5)
                .subscribe(System.out::println);
        // obtain result:
        // [1, 2, 3, 4, 5]
        // [6, 7, 8, 9, 10]
        // [11, 12, 13, 14, 15]
        // ...
    }

    private static void bufferCompanion() {
        Flux.range(1, 100)
                .delayElements(Duration.ofMillis(50))
                .buffer(Flux.range(1, 3).delayElements(Duration.ofMillis(300), Schedulers.newElastic("inner")))
                .publishOn(Schedulers.immediate())
                .subscribe(System.out::println);

        // obtain result:
        // [1, 2, 3, 4, 5]
        // [6, 7, 8, 9, 10, 11]
        // [12, 13, 14, 15, 16, 17]
    }

    private static void bufferDuration() {
        Flux.range(1, 100)
                .delayElements(Duration.ofMillis(10), Schedulers.newElastic("FluxBuffer"))
                .buffer(Duration.ofMillis(50), Duration.ofMillis(300))
                .subscribe(System.out::println);

        // obtain result:
        // [1, 2, 3, 4]
        // [24, 25, 26, 27, 28]
        // [52, 53, 54, 55, 56]
        // [79, 80, 81, 82, 83]
    }
}
