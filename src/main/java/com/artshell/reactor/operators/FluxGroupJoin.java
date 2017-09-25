package com.artshell.reactor.operators;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.function.BiFunction;
import java.util.function.Function;

public class FluxGroupJoin {

    /**
     * @see reactor.core.publisher.Flux#groupJoin(Publisher, Function, Function, BiFunction)
     */
    public static void main(String[] args) {
        Flux.just("B", "D", "E")
                .groupJoin(Flux.just(2, 4, 5), s -> Flux.just(s + "$").delayElements(Duration.ofMillis(50)), i -> Flux.just(i + "#").delayElements(Duration.ofMillis(60)), (left, f) -> f.map(i -> left + "-"+ i))
                .subscribe(f -> f.subscribe(System.out::println));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // obtain result:
        // B-2
        // D-2
        // E-2
        // B-4
        // D-4
        // E-4
        // B-5
        // D-5
        // E-5
    }
}
