package com.artshell.reactor.operators;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

/**
 * @see Flux#blockLast()
 * @see Flux#blockLast(Duration)
 */
public class FluxBlockLast {
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

    public static void main(String[] args) {
        // delay and blockLast
        try {
            Integer last = Flux.just(1,23,12,15).delayElements(Duration.ofMillis(200)).blockLast();
            System.out.println(last);
            // obtain result:
            // 15
        } catch (Exception e) {
            e.printStackTrace();
        }

        // delay and blockLast on duration
        try {
//            Integer last = Flux.just(1,23,12,15).delayElements(Duration.ofMillis(200)).blockLast(Duration.ofMillis(200));
//            System.out.println(last);
            // obtain result:
            // IllegalStateException: Timeout on blocking read for 200 MILLISECONDS
        } catch (Exception e) {
            e.printStackTrace();
        }


        // blockLast on duration
        try {
            Integer last = Flux.just(5, 7, 8).blockLast(Duration.ofMillis(100));
            System.out.println(last);
            // obtain result:
            // 8
        } catch (Exception e) {
            e.printStackTrace();
        }

        // delay per element and blockLast
        Integer last = Flux.concat(one.delayElements(Duration.ofMillis(200), Schedulers.newElastic("FirstSource")),
                two.delayElements(Duration.ofMillis(500), Schedulers.newElastic("TwoSource")),
                three.delayElements(Duration.ofSeconds(1), Schedulers.newElastic("ThreeSource")))
                .blockLast();
        System.out.println(last);
        // obtain result:
        // 3


        // delay per element and blockLast on duration
        Integer last2 = Flux.concat(one.delayElements(Duration.ofMillis(200), Schedulers.newElastic("FirstSource")),
                two.delayElements(Duration.ofMillis(500), Schedulers.newElastic("TwoSource")),
                three.delayElements(Duration.ofSeconds(1), Schedulers.newElastic("ThreeSource")))
                .blockLast(Duration.ofMillis(300));

        System.out.println(last2);
        // obtain result:
        // IllegalStateException: Timeout on blocking read for 300 MILLISECONDS


        // delay per element and blockLast on duration
        Integer last3 = Flux.concat(one.delayElements(Duration.ofMillis(200), Schedulers.newElastic("FirstSource")),
                two.delayElements(Duration.ofMillis(500), Schedulers.newElastic("TwoSource")),
                three.delayElements(Duration.ofSeconds(1), Schedulers.newElastic("ThreeSource")))
                .blockLast(Duration.ofSeconds(2));

        System.out.println(last3);
        // obtain result:
        // 3
    }
}
