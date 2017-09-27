package com.artshell.reactor.operators;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;

public class FluxMergeDelayError {


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

    // five Publisher
    private static Flux<Integer> five = Flux.create(emitter -> {
        if (!emitter.isCancelled()) {
            emitter.next(5);
            emitter.complete();
        }
    });

    // six Publisher
    private static Flux<Integer> six = Flux.create(emitter -> {
        if (!emitter.isCancelled()) {
            emitter.next(6);
            emitter.complete();
        }
    });

    private static void merge() {
        Flux.mergeDelayError(3, one, two, three.flatMap(i -> Flux.error(new IllegalArgumentException())), four, five, six)
                .subscribe(System.out::println, Throwable::printStackTrace);

        // obtain result:
        // 1
        // 2
        // 4
        // 5
        // 6
        // java.lang.IllegalArgumentException
    }

    /**
     * @see reactor.core.publisher.Flux#mergeDelayError(int, Publisher[])
     */
    public static void main(String[] args) {
        merge();
    }
}
