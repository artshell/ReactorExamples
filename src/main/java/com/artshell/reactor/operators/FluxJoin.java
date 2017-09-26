package com.artshell.reactor.operators;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.function.BiFunction;
import java.util.function.Function;

public class FluxJoin {

    /**
     * @see Flux#join(Publisher, Function, Function, BiFunction)
     */
    public static void main(String[] args) {
        Flux.range(1, 3)
                .join(Flux.just("A", "B", "C", "D", "E"), i -> Flux.just(i).delayElements(Duration.ofMillis(50)), s -> Flux.just(s).delayElements(Duration.ofMillis(60)), (left, right) -> left + right)
                .subscribe(System.out::println);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // obtain result:
        // 1A
        // 2A
        // 3A

        // 1B
        // 2B
        // 3B

        // 1C
        // 2C
        // 3C

        // 1D
        // 2D
        // 3D

        // 1E
        // 2E
        // 3E
    }
}
