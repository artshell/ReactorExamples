package com.artshell.reactor.operators;

import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * @see Flux#blockFirst()
 * @see Flux#blockFirst(Duration)
 */
public class FluxBlockFirst {

    public static void main(String[] args) {
        // blockFirst
        try {
            Integer first = Flux.just(1,23,12,15).delayElements(Duration.ofMillis(200)).blockFirst();
            System.out.println(first);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // obtain result:
        // 1


        // blockFirst on duration
        try {
            Integer first = Flux.just(1,23,12,15).delayElements(Duration.ofSeconds(1)).blockFirst(Duration.ofMillis(500));
            System.out.println(first);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // obtain result:
        // IllegalStateException: Timeout on blocking read for 500 MILLISECONDS
    }
}
