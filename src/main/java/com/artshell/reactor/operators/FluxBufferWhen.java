package com.artshell.reactor.operators;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @see reactor.core.publisher.Flux#bufferWhen(Publisher, Function)
 */
public class FluxBufferWhen {
    private static Integer[] companion = {1, 2, 3, 4};

    // first Publisher
    private static Flux<Integer> one = Flux.create(emitter -> {
        int len = companion.length;
        for (int i = 0; i < len && !emitter.isCancelled(); i++) {
            emitter.next(companion[i]);
        }

        if (!emitter.isCancelled()) {
            emitter.complete();
        }
    });

    // second Publisher
    private static Flux<Integer> two = Flux.create(emitter -> {
        int len = companion.length;
        for (int i = 0; i < len && !emitter.isCancelled(); i++) {
            emitter.next(companion[i]);
        }

        if (!emitter.isCancelled()) {
            emitter.complete();
        }
    });

    // three Publisher
    private static Flux<Integer> three = Flux.create(emitter -> {
        int len = companion.length;
        for (int i = 0; i < len && !emitter.isCancelled(); i++) {
            emitter.next(companion[i]);
        }

        if (!emitter.isCancelled()) {
            emitter.complete();
        }
    });

    // four Publisher
    private static Flux<Integer> four = Flux.create(emitter -> {
        int len = companion.length;
        for (int i = 0; i < len && !emitter.isCancelled(); i++) {
            emitter.next(companion[i]);
        }

        if (!emitter.isCancelled()) {
            emitter.complete();
        }
    });

    public static void main(String[] args) {
        Flux.concat(
                one.delayElements(Duration.ofMillis(100), Schedulers.newElastic("one")),
                two.delayElements(Duration.ofMillis(90), Schedulers.newElastic("two")),
                three.delayElements(Duration.ofMillis(150), Schedulers.newElastic("three")),
                four.delayElements(Duration.ofMillis(200), Schedulers.newElastic("four")))
                .bufferWhen(Flux.range(2, 8).delayElements(Duration.ofMillis(200)), i -> Flux.just(true).delayElements(Duration.ofMillis(200)))
                .subscribe(System.out::println);

        // obtain result:
        // [2, 3]
        // [4, 1]
        // [2, 3, 4]
        // [1]
        // [2]
        // [3, 4]
        // [1]
        // [2]
    }
}
