package com.artshell.reactor.operators;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @see Flux#bufferUntil(Predicate, boolean)
 */
public class FluxBufferUntil {

    public static void main(String[] args) {
        Flux.range(1, 10)
                .bufferUntil(i -> (i / 5) == 0, false)
                .subscribe(System.out::println);

        // obtain result:
        // [1]
        // [2]
        // [3]
        // [4]
        // [5, 6, 7, 8, 9, 10]


        Flux.range(1, 20)
                .bufferUntil(i -> (i % 3) == 0, false)
                .subscribe(System.out::println);

        // obtain result:
        // [1, 2, 3]
        // [4, 5, 6]
        // [7, 8, 9]
        // [10, 11, 12]
        // [13, 14, 15]
        // [16, 17, 18]
        // [19, 20]


        Flux.range(1, 20)
                .bufferUntil(i -> (i % 3) == 0, true)
                .subscribe(System.out::println);

        // obtain result:
        // [1, 2]
        // [3, 4, 5]
        // [6, 7, 8]
        // [9, 10, 11]
        // [12, 13, 14]
        // [15, 16, 17]
        // [18, 19, 20]
    }
}
