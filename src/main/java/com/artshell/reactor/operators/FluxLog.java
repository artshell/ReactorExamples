package com.artshell.reactor.operators;

import reactor.core.publisher.Flux;
import reactor.core.publisher.SignalType;

import java.util.logging.Level;

/**
 *
 */
public class FluxLog {

    /**
     * @see Flux#log()
     */
    private static void log() {
        Flux.range(1, 2)
                .log()
                .subscribe(System.out::println);

        // obtain result:
        // [ INFO] (main) | onSubscribe([Synchronous Fuseable] FluxRange.RangeSubscription)
        // [ INFO] (main) | request(unbounded)
        // [ INFO] (main) | onNext(1)
        // 1
        // [ INFO] (main) | onNext(2)
        // 2
        // [ INFO] (main) | onComplete()
    }

    /**
     * @see Flux#log(String, Level, SignalType...)
     */
    private static void logLevel() {
        Flux.range(3, 2)
                .log("FluxLog", Level.INFO, SignalType.ON_NEXT)
                .subscribe(System.out::println);

        // obtain result:
        // [ INFO] (main) | onNext(3)
        // 3
        // [ INFO] (main) | onNext(4)
        // 4
    }

    /**
     * @see Flux#log(String, Level, boolean, SignalType...)
     */
    private static void logShow() {
        Flux.range(5, 3)
                .log("FluxLog", Level.INFO, true, SignalType.ON_NEXT)
                .subscribe(System.out::println);

        // obtain result:
        // [ INFO] (main) | onNext(5) 	Flux.log(FluxLog.java:42)
        // 5
        // [ INFO] (main) | onNext(6) 	Flux.log(FluxLog.java:42)
        // 6
        // [ INFO] (main) | onNext(7) 	Flux.log(FluxLog.java:42)
        // 7
    }

    public static void main(String[] args) {
//        log();
//        logLevel();
        logShow();
    }
}
