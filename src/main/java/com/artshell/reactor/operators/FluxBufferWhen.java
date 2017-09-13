package com.artshell.reactor.operators;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @see reactor.core.publisher.Flux#bufferWhen(Publisher, Function)
 */
public class FluxBufferWhen {
    private static Integer[] companion = {1, 2, 3, 4};

    public static void main(String[] args) {
        Flux.range(1, 100)
                .bufferWhen(Flux.fromArray(companion), i -> Flux.just(true).delayElements(Duration.ofMillis(10)))
                .subscribe(System.out::println);
    }
}
