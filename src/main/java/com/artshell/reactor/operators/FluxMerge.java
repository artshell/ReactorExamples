package com.artshell.reactor.operators;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.ArrayList;

public class FluxMerge {

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
     * @see Flux#merge(Publisher[])
     * @see Flux#merge(int, Publisher[])
     *
     * @see Flux#mergeWith(Publisher)
     */
    private static void mergeVarargs() {
        Flux.merge(one, two, three, four)
                .subscribe(System.out::println);
        // obtain result:
        // 1, 2, 3, 4
    }

    /**
     * @see Flux#merge(Iterable)
     */
    private static void mergeIter() {
        ArrayList<Flux<Integer>> iter = new ArrayList<>();
        iter.add(two);
        iter.add(three);
        iter.add(four);
        Flux.merge(iter).subscribe(System.out::println);
        // obtain result:
        // 2, 3, 4
    }

    /**
     * @see Flux#merge(Publisher)
     * @see Flux#merge(Publisher, int)
     * @see Flux#merge(Publisher, int, int)
     */
    private static void mergeInnerPublisherConcurrency() {
        Flux.<Integer>merge(
                Flux.create(sink -> {
                    if (sink.isCancelled()) return;
                    sink.onRequest(n -> System.out.println("onRequest => " + n));
                    for (int i = 0; i < 12 && !sink.isCancelled(); i++) {
                        sink.next(Flux.just(i));
                    }
                    sink.complete();
                }, FluxSink.OverflowStrategy.BUFFER)
                , 2, 5).subscribe(System.out::println);

        // obtain result:
        // onRequest => 2
        // 0
        // 1
        // onRequest => 2
        // 2
        // 3
        // onRequest => 2
        // 4
        // 5
        // onRequest => 2
        // 6
        // 7
        // onRequest => 2
        // 8
        // 9
        // onRequest => 2
        // 10
        // 11
        // onRequest => 2
    }

    public static void main(String[] args) {
//        mergeVarargs();
//        mergeIter();
        mergeInnerPublisherConcurrency();
    }
}
