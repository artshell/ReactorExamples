package com.artshell.reactor.operators;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

public class FluxOr {

    /**
     * @see Flux#or(Publisher)
     * @see Flux#first(Publisher[])
     */
    private static void or() {
        Flux.just("A")
                .delayElements(Duration.ofMillis(100))
                .or(Flux.just("$").delayElements(Duration.ofMillis(50), Schedulers.newSingle("FluxOr")))
                .subscribe(System.out::println);
        // obtain result:
        // $
    }

    public static void main(String[] args) {
        or();
    }
}
