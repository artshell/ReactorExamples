package com.artshell.reactor.operators;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class FluxSample {
    /**
     * @see Flux#sample(Duration)
     * @see Flux#sample(Publisher)
     */
    private static void sample() {
        Flux.range(1, 10000)
                .sample(Duration.ofMillis(5))
                .subscribe(System.out::println);
        // obtain result:
        // 9767
        // 10000
    }

    public static void main(String[] args) {
        sample();
    }
}
