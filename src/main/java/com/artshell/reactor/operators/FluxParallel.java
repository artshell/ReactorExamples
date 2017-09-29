package com.artshell.reactor.operators;

import reactor.core.publisher.Flux;
import reactor.core.publisher.ParallelFlux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

public class FluxParallel {
    /**
     * @see ParallelFlux#runOn(Scheduler)
     * @see ParallelFlux#sequential()
     */
    private static void parallelSequential() {
        Flux.just(System.currentTimeMillis())
                .repeat()
                .parallel(2)
                .runOn(Schedulers.parallel())
                .sequential()
                .subscribe(System.out::println);

        // obtain result:
        // 1506662929053, 1506662929053, 1506662929053 ...
    }

    /**
     * @see ParallelFlux#groups()
     */
    private static void parallelGroup() {
        Flux.just(System.currentTimeMillis())
                .repeat()
                .parallel(2)
                .runOn(Schedulers.parallel())
                .groups()
                .subscribe(g -> {
                    System.out.println("paralle key=> " + g.key());
                    g.subscribe(n -> System.out.println("paralle value=> " + n));
                });
        // obtain result:
        // paralle key=> 0
        // paralle key=> 1
        // paralle value=> 1506663658851
        // paralle value=> 1506663658851
        // ... more
    }

    public static void main(String[] args){
//        parallelSequential();

        parallelGroup();
    }
}
