package com.artshell.reactor.operators;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.function.BiFunction;

public class FluxWithLatestFrom {

    /**
     * @see reactor.core.publisher.Flux#withLatestFrom(Publisher, BiFunction)
     */
    public static void main(String[] args) {
        Flux.range(1, 5).delayElements(Duration.ofMillis(50))
                .withLatestFrom(Flux.just("#", "$", "&", "%", "@", "*").delayElements(Duration.ofMillis(100)), (l, r) -> l + " - " + r)
                .subscribe(System.out::println);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // obtain result:
        // 1 - #
        // 2 - #
        // 3 - $
        // 4 - &
        // 5 - %

        // 1 - #
        // 2 - &
        // 3 - %
        // 4 - *
        // 5 - *

        // 2 - #
        // 3 - #
        // 4 - $
        // 5 - $
    }
}
