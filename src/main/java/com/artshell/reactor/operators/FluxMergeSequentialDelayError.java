package com.artshell.reactor.operators;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

public class FluxMergeSequentialDelayError {
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


    /**
     * @see Flux#mergeSequentialDelayError(int, Publisher[])
     * @see Flux#mergeSequentialDelayError(Iterable, int, int)
     * @see Flux#mergeSequentialDelayError(Publisher, int, int)
     */
    public static void main(String[] args) {
        Flux.mergeSequentialDelayError(3,
                one,
                two.flatMap(i -> Flux.error(new IllegalArgumentException())),
                three.delayElements(Duration.ofMillis(10), Schedulers.newSingle("Sequential3")),
                four)
                .subscribe(System.out::println, Throwable::printStackTrace);

        // obtain result:
        // 1, 3, 4
        // java.lang.IllegalArgumentException
    }
}
