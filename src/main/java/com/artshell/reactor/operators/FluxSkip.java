package com.artshell.reactor.operators;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.function.Predicate;

public class FluxSkip {
    /**
     * @see reactor.core.publisher.Flux#skip(Duration)
     * @see reactor.core.publisher.Flux#skip(Duration, Scheduler)
     */
    private static void skipDur() {
        Flux.concat(
                Flux.just("%").delayElements(Duration.ofMillis(10)),
                Flux.just("$").delayElements(Duration.ofMillis(100)))
                .skip(Duration.ofMillis(50), Schedulers.newSingle("duration"))
                .subscribe(System.out::println);

        // obtain result:
        // $
    }

    /**
     * @see Flux#skip(long)
     */
    private static void skipCount() {
        Flux.range(1, 5)
                .skip(2)
                .subscribe(System.out::println);

        // obtain result:
        // 3, 4, 5
    }

    /**
     * @see Flux#skipLast(int)
     */
    private static void skipLast() {
        Flux.range(1, 5)
                .skipLast(3)
                .subscribe(System.out::println);

        // obtain result:
        // 1,2
    }

    /**
     * @see Flux#skipUntil(Predicate)
     */
    private static void skipUntil() {
        Flux.range(1, 10)
                .skipUntil(i -> i > 5)
                .subscribe(System.out::println);
        // obtain result:
        // 6, 7, 8, 9, 10

        Flux.range(1, 5)
                .skipUntil(i -> i != 3)
                .subscribe(System.out::println);
        // obtain result:
        // 1, 2, 3, 4, 5
    }

    /**
     * @see Flux#skipWhile(Predicate)
     */
    private static void skipWhile() {
        Flux.range(1, 10)
                .skipWhile(i -> i < 4)
                .subscribe(System.out::println);
        // obtain result:
        // 4, 5, 6, 7, 8, 9, 10
    }

    /**
     * @see Flux#skipUntilOther(Publisher)
     */
    private static void skipOther() {
        Flux.concat(
                Flux.just("%").delayElements(Duration.ofMillis(10)),
                Flux.just("@").delayElements(Duration.ofMillis(30)),
                Flux.just("#").delayElements(Duration.ofMillis(50)),
                Flux.just("&").delayElements(Duration.ofMillis(150)),
                Flux.just("$").delayElements(Duration.ofMillis(100)))
                .skipUntilOther(Flux.just(true).delayElements(Duration.ofMillis(30), Schedulers.newSingle("other")))
                .subscribe(System.out::println);
        // obtain result:
        // @, #, &, $
    }

    public static void main(String[] args) {
//        skipDur();
//        skipCount();
//        skipLast();
        skipUntil();
//        skipOther();
//        skipWhile();
    }

}
