package com.artshell.reactor.operators;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

public class FluxDelaySubscription {

    /**
     * @see reactor.core.publisher.Flux#delaySubscription(Duration)
     */
    private static void delaySubscriptionDuration() {
        Flux.just(5)
                .delaySubscription(Duration.ofMillis(500), Schedulers.parallel())
                .subscribe(System.out::println, Throwable::printStackTrace);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // obtain result after 1 seconds
        // 5
    }

    public static void main(String[] args) {
        delaySubscriptionDuration();
    }
}
