package com.artshell.reactor.operators;

import reactor.core.publisher.Mono;
import reactor.core.publisher.ParallelFlux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.function.Function;

public class FluxParallel {
    /**
     * @see ParallelFlux#runOn(Scheduler)
     * @see ParallelFlux#sequential()
     */
    private static void parallelSequential() {
        Mono.fromCallable(System::currentTimeMillis)
                .repeat()
                .parallel(2)
                .runOn(Schedulers.parallel())
                .sequential()
                .subscribe(System.out::println);

        // obtain result:
        // 1506778053111
        // ...
        // 1506778053112
        // ...
        // 1506778053113
        // ...
        // 1506778053114
        // 1506778053113
        // 1506778053114
        // 1506778053113
        // 1506778053114
        // 1506778053115
        // 1506778053113
        // 1506778053114
        // 1506778053116
        // 1506778053117
        // 1506778053118
        // 1506778053119
        // 1506778053120
        // 1506778053121
        // more
    }

    /**
     * @see ParallelFlux#groups()
     */
    private static void parallelGroup() {
        Mono.fromCallable(System::currentTimeMillis)
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
        // paralle value=> 1506778403606
        // ...
        // paralle value=> 1506778403607
        // ...
        // paralle value=> 1506778403608
        // paralle value=> 1506778403607
        // paralle value=> 1506778403609
        // ...
        // paralle value=> 1506778403610
        // ...
    }

    /**
     * @see ParallelFlux#composeGroup(Function)
     */
    private static void paralleComposeGroup() {
        Mono.fromCallable(System::currentTimeMillis)
                .repeat()
                .parallel(2)
                .runOn(Schedulers.parallel())
                .composeGroup(g -> g)
                .subscribe(System.out::println);

        // obtain result:
        // 1506780263056
        // 1506780263056
        // ...
        // 1506780263057
        // 1506780263056
        // ...
        // 1506780263057
        // ...
        // 1506780263058
        // ...
        // 1506780263059
        // ...
        // 1506780263058
        // ...
        // 1506780263059
        // ...
        // 1506780263060
        // ...
        // 1506780263059
        // 1506780263060
        // ...
        // 1506780263061
        // ...
        // 1506780263062
        // ...
        // 1506780263059
        // 1506780263062
        // ...
        // 1506780263059
        // 1506780263062
        // ...
        // 1506780263063
        // ...
        // 1506780263059
        // 1506780263063
        // ...
        // 1506780263059
        // 1506780263063
        // ...
        // 1506780263059
        // 1506780263063
    }

    public static void main(String[] args){
//        parallelSequential();
//        parallelGroup();
        paralleComposeGroup();
    }
}
