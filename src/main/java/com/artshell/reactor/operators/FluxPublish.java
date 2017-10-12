package com.artshell.reactor.operators;

import org.reactivestreams.Subscription;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.function.Consumer;

public class FluxPublish {
    private static ConnectableFlux<Integer> getFlux() {
        return Flux.range(1, 5).publish();
    }

    /**
     * @see Flux#publish()
     * @see ConnectableFlux#autoConnect()
     * @see ConnectableFlux#autoConnect(int)
     * @see ConnectableFlux#autoConnect(int, Consumer)
     */
    private static void autoConnect() {
        Flux<Integer> flux = getFlux().autoConnect(3);

        // first subscriber
        flux.subscribe(new CoreSubscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription s) {
                s.request(Integer.MAX_VALUE);
            }

            @Override
            public void onNext(Integer i) {
                System.out.println("first = " + i);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        });


        // second subscriber
        flux.subscribe(new CoreSubscriber<Integer>() {
            Subscription s;

            @Override
            public void onSubscribe(Subscription s) {
                this.s = s;
                s.request(Integer.MAX_VALUE);
            }

            @Override
            public void onNext(Integer i) {
                System.out.println("second = " + i);
                if (i == 3) {
                    s.cancel();
                }
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        });

        // three subscriber
        flux.subscribe(new CoreSubscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription s) {
                s.request(Integer.MAX_VALUE);
            }

            @Override
            public void onNext(Integer i) {
                System.out.println("three = " + i);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        });


        // obtain result:
        // first = 1
        // second = 1
        // three = 1
        // first = 2
        // second = 2
        // three = 2
        // first = 3
        // second = 3
        // three = 3
        // first = 4
        // three = 4
        // first = 5
        // three = 5
    }

    /**
     * @see Flux#share()
     * @see ConnectableFlux#refCount()
     * @see ConnectableFlux#refCount(int)
     */
    private static void refCount() {
        Flux<String> flux = Flux.just("A", "B", "C").publish().refCount(2);

        // waiting when first publisher subscribed
        flux.subscribe(System.out::println);

        // begin emmit element when second publisher subscribed
        flux.subscribe(System.out::println);

        // obtain result:
        // A, A, B, B, C, C
    }

    /**
     * @see ConnectableFlux#refCount(int, Duration)
     * @see ConnectableFlux#refCount(int, Duration, Scheduler)
     */
    private static void refCountGrace() {
        Flux<Integer> flux = Flux.range(1, 3).publish().refCount(1, Duration.ofMillis(50), Schedulers.newSingle("FluxPublish"));

        // first Subscriber
        flux.subscribe(new CoreSubscriber<Integer>() {
            Subscription s;

            @Override
            public void onSubscribe(Subscription s) {
                this.s = s;
                s.request(Integer.MAX_VALUE);
            }

            @Override
            public void onNext(Integer n) {
                System.out.println("First Subscriber => " + n);
                s.cancel();
                testGrace(flux);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        });

        // obtain result:
        // First Subscriber => 1
    }

    private static void testGrace(Flux<Integer> flux) {
        // Second Subscriber
        flux.subscribe(new CoreSubscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription s) {
                s.request(Integer.MAX_VALUE);
            }

            @Override
            public void onNext(Integer n) {
                System.out.println("Second Subscriber => " + n);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        });

        // obtain result:
        // Second Subscriber => 2
        // Second Subscriber => 3
    }

    public static void main(String[] args) {
//        autoConnect();
//        refCount();
        refCountGrace();
    }
}
