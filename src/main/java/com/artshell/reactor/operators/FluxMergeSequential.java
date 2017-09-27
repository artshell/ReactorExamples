package com.artshell.reactor.operators;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.ArrayList;

public class FluxMergeSequential {
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

    /**
     * @see Flux#mergeSequential(Publisher[])
     * @see Flux#mergeSequential(int, Publisher[])
     */
    private static void mergeSequentialVarargs() {
        Flux.mergeSequential(3,
                one,
                two.delayElements(Duration.ofMillis(150), Schedulers.newSingle("Sequential2")),
                three.delayElements(Duration.ofMillis(10), Schedulers.newSingle("Sequential3")),
                four, five, six)
                .subscribe(System.out::println, Throwable::printStackTrace);

        // obtain result:
        // 1, 2, 3, 4, 5, 6
    }

    /**
     * @see Flux#mergeSequential(Iterable)
     * @see Flux#mergeSequential(Iterable, int, int)
     */
    private static void mergeSequentialIter() {
        ArrayList<Publisher<Integer>> array = new ArrayList<>();
        array.add(one);
        array.add(two.delayElements(Duration.ofMillis(150), Schedulers.newSingle("Sequential2")));
        array.add(three);
        array.add(four);
        array.add(five.delayElements(Duration.ofMillis(10), Schedulers.newSingle("Sequential5")));
        array.add(six);
        Flux.mergeSequential(array, 2, 1)
                .doOnRequest(n -> System.out.println("onRequest => " + n))
                .subscribe(System.out::println);

        // obtain result:
        // onRequest => 9223372036854775807
        // 1, 2, 3, 4, 5, 6
    }

    /**
     * @see Flux#mergeSequential(Publisher)
     * @see Flux#mergeSequential(Publisher, int, int)
     */
    private static void mergeSequentialPublisher() {
        Flux.<String>mergeSequential(
                Flux.create(sink -> {
                    if (sink.isCancelled()) return;
                    sink.onRequest(n -> System.out.println("onRequest => " + n));
                    for (int i = 0; i < 12 && !sink.isCancelled(); i++) {
                        sink.next(Flux.just(i + "#"));
                    }
                    sink.complete();
                }, FluxSink.OverflowStrategy.BUFFER)
                , 2, 3).subscribe(System.out::println);

        // obtain result:
        // onRequest => 2
        // 0#
        // onRequest => 1
        // 1#
        // onRequest => 1
        // 2#
        // onRequest => 1
        // 3#
        // onRequest => 1
        // 4#
        // onRequest => 1
        // 5#
        // onRequest => 1
        // 6#
        // onRequest => 1
        // 7#
        // onRequest => 1
        // 8#
        // onRequest => 1
        // 9#
        // onRequest => 1
        // 10#
        // onRequest => 1
        // 11#
        // onRequest => 1
    }

    public static void main(String[] args) {
//        mergeSequentialVarargs();
//        mergeSequentialIter();
        mergeSequentialPublisher();
    }
}
