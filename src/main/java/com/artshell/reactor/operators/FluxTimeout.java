package com.artshell.reactor.operators;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.function.Function;

public class FluxTimeout {
    /**
     * @see reactor.core.publisher.Flux#timeout(Duration)
     * @see reactor.core.publisher.Flux#timeout(Duration, Publisher)
     * @see reactor.core.publisher.Flux#timeout(Duration, Scheduler)
     * @see reactor.core.publisher.Flux#timeout(Duration, Publisher, Scheduler)
     */
    private static void timeoutScheduler() {
        Flux.concat(
                Flux.just("$").delayElements(Duration.ofMillis(50)),
                Flux.just("@").delayElements(Duration.ofMillis(100)),
                Flux.just("%").delayElements(Duration.ofMillis(80)))
                .timeout(Duration.ofMillis(60), Flux.just("timeout"), Schedulers.newElastic("Scheduler"))
                .subscribe(System.out::println);

        // obtain result:
        // $, timeout
    }

    /**
     * @see reactor.core.publisher.Flux#timeout(Publisher)
     * @see reactor.core.publisher.Flux#timeout(Publisher, Function)
     *
     * firstTimeout the timeout {@link Publisher} that must not emit before the first signal from this {@link Flux}
     */
    private static void timeoutPerElement() {
        Flux.concat(
                Flux.just("$").delayElements(Duration.ofMillis(50)),
                Flux.just("##").delayElements(Duration.ofMillis(100)),
                Flux.just("%%").delayElements(Duration.ofMillis(80)))
                .timeout(Flux.just("first timeout publisher").delayElements(Duration.ofMillis(100), Schedulers.newElastic("first")), s -> Flux.just(s).delayElements(Duration.ofMillis(s.length() * 100), Schedulers.newElastic("next")))
                .subscribe(System.out::println);

        // obtain result:
        // $, ##, %%
    }

    /**
     * @see reactor.core.publisher.Flux#timeout(Publisher, Function, Publisher)
     */
    private static void timeoutPerFallback() {
        Flux.concat(
                Flux.just("$").delayElements(Duration.ofMillis(50)),
                Flux.just("##").delayElements(Duration.ofMillis(100)),
                Flux.just("%%%").delayElements(Duration.ofMillis(80)))
                .timeout(Flux.just("first timeout publisher").delayElements(Duration.ofMillis(60), Schedulers.newElastic("first")), s -> Flux.just(s).delayElements(Duration.ofMillis(s.length() * 100), Schedulers.newElastic("next")), Flux.just("timeout"))
                .subscribe(System.out::println);

        // obtain result:
        // $, ##, %%%
    }

    public static void main(String[] args) {
//        timeoutScheduler();
//        timeoutPerElement();

        timeoutPerFallback();
    }
}
