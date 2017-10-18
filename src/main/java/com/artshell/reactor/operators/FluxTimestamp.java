package com.artshell.reactor.operators;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;

public class FluxTimestamp {
    /**
     * @see Flux#timestamp()
     * @see Flux#timestamp(Scheduler)
     */
    public static void main(String[] args) {
        Flux.just("#")
                .timestamp()
                .subscribe(tuple -> System.out.println("Timestamp => " + tuple.getT1() + ", value => " + tuple.getT2()));

        // obtain result:
        // Timestamp => 1508318209263, value => #
    }
}
