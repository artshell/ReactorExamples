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
                .delaySubscription(Duration.ofSeconds(1), Schedulers.newElastic("FluxDelaySubscription"))
                .doOnSubscribe(s -> System.out.println("doOnSubscribe"))
                .subscribe(System.out::println, Throwable::printStackTrace);

        // obtain result after 1 seconds
        // 5
    }

    public static void main(String[] args) {
        delaySubscriptionDuration();
    }
}
