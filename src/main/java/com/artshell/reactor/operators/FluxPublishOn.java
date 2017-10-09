package com.artshell.reactor.operators;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

public class FluxPublishOn {

    /**
     * @see reactor.core.publisher.Flux#publishOn(Scheduler)
     * @see reactor.core.publisher.Flux#publishOn(Scheduler, int)
     * @see Flux#publishOn(Scheduler, boolean, int)
     */
    private static void publishOn() {
        Flux.range(5, 4)
                .doOnRequest(l -> System.out.println("doOnRequest => " + Thread.currentThread().getName()))
                .doOnSubscribe(s -> System.out.println("doOnSubscribe => " + Thread.currentThread().getName()))
                .subscribeOn(Schedulers.newParallel("Parallel"))
                .publishOn(Schedulers.newElastic("Elastic"))
                .doOnNext(i -> System.out.println("publishOn => " + Thread.currentThread().getName()))
                .subscribe(System.out::println);

        // obtain result:
        // doOnSubscribe => Parallel-1
        // doOnRequest => Parallel-1
        // publishOn => Elastic-2
        // 5
        // publishOn => Elastic-2
        // 6
        // publishOn => Elastic-2
        // 7
        // publishOn => Elastic-2
        // 8
    }

    public static void main(String[] args) {
        publishOn();
    }
}
