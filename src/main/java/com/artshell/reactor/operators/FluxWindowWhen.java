package com.artshell.reactor.operators;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.function.Function;

public class FluxWindowWhen {
    /**
     * @see reactor.core.publisher.Flux#windowWhen(Publisher, Function)
     */
    public static void main(String[] args) {
        Flux.concat(Flux.just("A", "BB", "CC", "D", "R").delayElements(Duration.ofMillis(100)), Flux.just("!", "@@", "#", "$", "%%","^", "&", "**").delayElements(Duration.ofMillis(30)))
                .windowWhen(Flux.just("$"), s -> Flux.just(s + "@").delayElements(Duration.ofMillis(300)))
                .flatMap(flx -> flx)
                .subscribe(System.out::println);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // obtain result:
        // A, BB
    }
}
