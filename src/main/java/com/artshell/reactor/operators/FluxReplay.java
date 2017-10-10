package com.artshell.reactor.operators;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;

import java.time.Duration;

public class FluxReplay {
    /**
     * @see Flux#replay()
     * @see Flux#replay(int)
     */
    private static void replayHistory() {
        Flux<Integer> replay = Flux.range(1, 2).replay(2).autoConnect(2);

        replay.subscribe(i -> System.out.println("First subscriber => " + i));
        replay.subscribe(i -> System.out.println("Second subscriber => " + i));
        // obtain result:
        // First subscriber => 1
        // Second subscriber => 1
        // First subscriber => 2
        // Second subscriber => 2
    }

    /**
     * @see Flux#replay(Duration)
     * @see Flux#replay(Duration, Scheduler)
     * @see Flux#replay(int, Duration)
     * @see Flux#replay(int, Duration, Scheduler)
     */
    private static void replayDuration() {
        Flux.concat(Flux.just("A"), Flux.just("B"))
                .replay(Duration.ofMillis(100))
                .autoConnect()
                .subscribe(System.out::println);

        // obtain result:
        // A, B
    }

    public static void main(String[] args) {
//        replayHistory();
        replayDuration();
    }
}
