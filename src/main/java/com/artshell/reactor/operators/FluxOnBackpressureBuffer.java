package com.artshell.reactor.operators;

import reactor.core.publisher.BufferOverflowStrategy;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.function.Consumer;

public class FluxOnBackpressureBuffer {

    /**
     * @see Flux#onBackpressureBuffer()  unbounded = true
     * @see Flux#onBackpressureBuffer(int)    unbounded = false
     */
    private static void onBackpressureBuffer() {
        Flux.range(4, 100)
                .doOnRequest(n -> System.out.println("OnRequest => " + n))
                .onBackpressureBuffer()
                .subscribe(System.out::println);

        // obtain result:
        // OnRequest => 9223372036854775807
        // 4, 5, 6, 7, 8, 9 ...
    }

    /**
     * @see Flux#onBackpressureBuffer(int, Consumer)
     */
    private static void onBackpressureBufferOverflow() {
        Flux.range(1, 100000)
                .doOnRequest(n -> System.out.println("OnRequest => " + n))
                .onBackpressureBuffer(10, i -> System.out.println("Over => " + i))
                .delayElements(Duration.ofMillis(10))
                .subscribe(System.out::println);
        // obtain result:
        // OnRequest => 9223372036854775807
        // Over => 18
        // Exception in thread "main" reactor.core.Exceptions$ErrorCallbackNotImplemented: reactor.core.Exceptions$OverflowException: The receiver is overrun by more signals than expected (bounded queue...)
        // Caused by: reactor.core.Exceptions$OverflowException: The receiver is overrun by more signals than expected (bounded queue...)
    }

    /**
     * @see Flux#onBackpressureBuffer(int, BufferOverflowStrategy)
     */
    private static void onBackpressureBufferStrategy() {
        Flux.range(1, 100000)
                .doOnRequest(n -> System.out.println("OnRequest => " + n))
                .onBackpressureBuffer(20, BufferOverflowStrategy.DROP_LATEST)
                .delayElements(Duration.ofMillis(1))
                .subscribe(System.out::println);
        // obtain result:
        // OnRequest => 9223372036854775807
        // 1, 2, 3, 4
    }

    /**
     * @see Flux#onBackpressureBuffer(int, Consumer, BufferOverflowStrategy)
     */
    private static void onBackpressureBufferConsumerStrategy() {
        Flux.range(5, 100000)
                .doOnRequest(n -> System.out.println("OnRequest => " + n))
                .onBackpressureBuffer(20, i -> System.out.println("Over => " + i), BufferOverflowStrategy.DROP_OLDEST)
                .delayElements(Duration.ofMillis(10))
                .subscribe(System.out::println);
        // obtain result:
        // OnRequest => 9223372036854775807
        // Over => 9032
        // Over => 9033
        // Over => 9034
        // Over => 9035
    }

    /**
     * @see Flux#onBackpressureBuffer(Duration, int, Consumer)
     * @see Flux#onBackpressureBuffer(Duration, int, Consumer, Scheduler)
     */
    private static void onBackpressureBufferConsumerDuration() {
        Flux.range(5, 100000)
                .doOnRequest(n -> System.out.println("OnRequest => " + n))
                .onBackpressureBuffer(Duration.ofMillis(20), 100, i -> System.out.println("Over => " + i), Schedulers.newSingle("block"))
                .delayElements(Duration.ofMillis(1), Schedulers.newSingle("delay"))
                .subscribe(System.out::println);
        // obtain result:
        // OnRequest => 9223372036854775807
        // 5
        // Exception in thread "main" reactor.core.Exceptions$ErrorCallbackNotImplemented: reactor.core.Exceptions$OverflowException: Queue is full: Reactive Streams source doesn't respect backpressure
        // Caused by: reactor.core.Exceptions$OverflowException: Queue is full: Reactive Streams source doesn't respect backpressure
    }

    public static void main(String[] args) {
//        onBackpressureBuffer();
//        onBackpressureBufferOverflow();
//        onBackpressureBufferStrategy();
//         onBackpressureBufferConsumerStrategy();
        onBackpressureBufferConsumerDuration();
    }
}
