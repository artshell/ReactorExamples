package com.artshell.reactor.operators;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

/**
 * @see reactor.core.publisher.Flux#first(Publisher[])
 * @see Flux#first(Iterable)
 */
public class FluxFirst {
    // first Publisher
    private static Flux<Integer> one = Flux.create(emitter -> {
        if (!emitter.isCancelled()) {
            emitter.next(1);
            emitter.complete();
        }
    });

    // second Publisher
    private static Flux<Integer> two = Flux.create(emitter -> {
        if (!emitter.isCancelled()) {
            emitter.next(2);
            emitter.complete();
        }
    });

    // three Publisher
    private static Flux<Integer> three = Flux.create(emitter -> {
        if (!emitter.isCancelled()) {
            emitter.next(3);
            emitter.complete();
        }
    });

    // four Publisher
    private static Flux<Integer> four = Flux.create(emitter -> {
        if (!emitter.isCancelled()) {
            emitter.next(4);
            emitter.complete();
        }
    });

    public static void main(String[] args) {

        // Flux#first() == Rxjava's Flowable#amb()
        Flux.first(
                one.delayElements(Duration.ofMillis(300), Schedulers.newElastic("FirstSource")),
                two.delayElements(Duration.ofSeconds(1), Schedulers.newElastic("TwoSource")),
                three.delayElements(Duration.ofMillis(200), Schedulers.newElastic("ThreeSource")),
                four.delayElements(Duration.ofSeconds(2), Schedulers.newElastic("FourOperator")))
                .subscribeOn(Schedulers.immediate())
                .publishOn(Schedulers.immediate())
                .subscribe(System.out::println, Throwable::printStackTrace);

        // obtain result:
        // 3
    }
}
