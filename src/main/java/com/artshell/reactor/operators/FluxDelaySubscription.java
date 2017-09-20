package com.artshell.reactor.operators;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

public class FluxDelaySubscription {

    /**
     * @see Flux#delaySubscription(Duration)
     * @see Flux#delaySubscription(Duration, Scheduler)
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

        // obtain result after 1 seconds:
        // 5
    }

    private static void delaySubscriptionPublisher() {
        Flux.range(1, 4)
                .delaySubscription(Flux.just(6).delayElements(Duration.ofMillis(300), Schedulers.newElastic("delay-publisher")))
                .subscribe(System.out::println);

        // obtain result after 300 mill:
        // 1
        // 2
        // 3
        // 4
    }

    public static void main(String[] args) {
//        delaySubscriptionDuration();
        delaySubscriptionPublisher();
    }
}
