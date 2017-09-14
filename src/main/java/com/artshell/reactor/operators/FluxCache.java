package com.artshell.reactor.operators;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import sun.misc.Cache;

import java.time.Duration;


/**
 * @see Flux#cache(int)
 * @see Flux#cache(int, Duration)
 */
public class FluxCache {


    public static void main(String[] args) {
//        cache();
        cacheDuration();
    }

    private static void cache() {
        Flux<Integer> cache = Flux.range(1, 5).cache(2 /* history */);

        // first subscriber
        cache.publishOn(Schedulers.immediate()).subscribe(i -> System.out.println("first => " + i));
        // second subscriber
        cache.publishOn(Schedulers.immediate()).subscribe(i -> System.out.println("second => " + i));

        // obtain result:
        // first => 1
        // first => 2
        // first => 3
        // first => 4
        // first => 5
        // second => 4
        // second => 5
    }

    private static void cacheDuration() {
        Flux<Integer> cache = Flux.concat( one, two, three, four)
                .cache(3, Duration.ofMillis(5));

        // first subscriber
        cache.subscribe(i -> System.out.println("first => " + i));
        // second subscriber
        cache.subscribe(i -> System.out.println("second => " + i));

        // obtain result:
        // first => 1
        // first => 2
        // first => 3
        // first => 4
        // second => 2
        // second => 3
        // second => 4
    }

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
}
