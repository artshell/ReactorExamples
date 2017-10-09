package com.artshell.reactor.operators;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

public class FluxDelayElements {
    /**
     * @see Flux#delayElements(Duration)
     */
    public static void main(String[] args) {
        Flux.range(3, 3)
                .delayElements(Duration.ofMillis(150), Schedulers.newSingle("delayElements"))
                .subscribe(System.out::println);
        // obtain result：
        // 3, 4, 5
    }
}
