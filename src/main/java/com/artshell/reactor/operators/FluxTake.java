package com.artshell.reactor.operators;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.function.Predicate;

public class FluxTake {
    /**
     * @see reactor.core.publisher.Flux#take(long)
     */
    private static void take() {
        Flux.range(1, 4)
                .take(2)
                .subscribe(System.out::println);

        // obtain result:
        // 1, 2
    }

    /**
     * @see Flux#take(Duration)
     * @see Flux#take(Duration, Scheduler)
     */
    private static void takeDuration() {
        Flux.range(1, 1000)
                .take(Duration.ofMillis(1), Schedulers.parallel())
                .subscribe(System.out::println);
        // obtain result:
        // 1, 2, 3, 4, 4, 5, 6, 7
    }

    /**
     * @see Flux#takeLast(int)
     */
    private static void takeLast() {
        Flux.range(2, 5)
                .takeLast(2)
                .subscribe(System.out::println);

        // obtain result:
        // 5, 6
    }

    /**
     * @see Flux#takeUntil(Predicate)
     */
    private static void takeUntil() {
        Flux.range(1, 10)
                .takeUntil(i -> i > 5)
                .subscribe(System.out::println);

        // obtain result:
        // 1, 2, 3, 4, 5, 6

        Flux.range(1, 5)
                .takeUntil(i -> i != 3)
                .subscribe(System.out::println);
        // obtain result:
        // 1
    }

    /**
     * @see Flux#takeUntilOther(Publisher)
     */
    private static void takeUntilOther() {
        Flux.concat(
                Flux.just("%").delayElements(Duration.ofMillis(10)),
                Flux.just("@").delayElements(Duration.ofMillis(30)),
                Flux.just("#").delayElements(Duration.ofMillis(50)),
                Flux.just("&").delayElements(Duration.ofMillis(150)),
                Flux.just("$").delayElements(Duration.ofMillis(100)))
                .takeUntilOther(Flux.just(true).delayElements(Duration.ofMillis(30), Schedulers.newSingle("other")))
                .subscribe(System.out::println);
        // obtain result:
        // %
    }

    /**
     * @see Flux#takeWhile(Predicate)
     */
    private static void takeWhile() {
        Flux.range(1, 10)
                .takeWhile(i -> i < 4)
                .subscribe(System.out::println);
        // obtain result:
        // 1, 2, 3
    }

    public static void main(String[] args) {
//        take();
//        takeDuration();
//        takeLast();
//        takeUntil();
//        takeUntilOther();
        takeWhile();
    }
}
