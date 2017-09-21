package com.artshell.reactor.operators;


import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class FluxSubscribe {
    /**
     * @see Flux#subscribe(Subscriber)
     */
    private static void subscribeSubscriber() {
        Flux.range(4, 2)
                .subscribeOn(Schedulers.newElastic("subscribeSubscriber"), false)
                .doOnSubscribe(s -> System.out.println("doOnSubscribe => " + Thread.currentThread()))
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(-1);
                        System.out.println("onSubscribe => " + Thread.currentThread());
                    }

                    @Override
                    public void onNext(Integer i) {
                        System.out.println("onNext => " + i + " " + Thread.currentThread());
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        // obtain result:
        // doOnSubscribe => Thread[main,5,main]
        // onSubscribe => Thread[main,5,main]
        // onNext element be ignored
    }

    /**
     * @see Flux#subscribe(CoreSubscriber)
     */
    private static void subscribeCoreSubscriber() {
        Flux.range(8, 2)
                .subscribeOn(Schedulers.newElastic("subscribeSubscriber"), false)
                .doOnSubscribe(s -> System.out.println("doOnSubscribe => " + Thread.currentThread()))
                .subscribe(new CoreSubscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(-1);
                        System.out.println("onSubscribe => " + Thread.currentThread());
                    }

                    @Override
                    public void onNext(Integer i) {
                        System.out.println("onNext => " + i + " " + Thread.currentThread());
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        // obtain result:
        // doOnSubscribe => Thread[main,5,main]
        // [DEBUG] (main) Negative request - java.lang.IllegalArgumentException: Spec. Rule 3.9 - Cannot request a non strictly positive number: -1
        // java.lang.IllegalArgumentException: Spec. Rule 3.9 - Cannot request a non strictly positive number: -1
        // at reactor.core.Exceptions.nullOrNegativeRequestException(Exceptions.java:276)
        // at reactor.core.publisher.Operators.reportBadRequest(Operators.java:503)
        // at reactor.core.publisher.Operators.validate(Operators.java:701)
        // at reactor.core.publisher.FluxSubscribeOn$SubscribeOnSubscriber.request(FluxSubscribeOn.java:172)
        // at reactor.core.publisher.FluxPeek$PeekSubscriber.request(FluxPeek.java:130)
        // at com.artshell.reactor.operators.FluxSubscribe$2.onSubscribe(FluxSubscribe.java:56)
        // at reactor.core.publisher.FluxPeek$PeekSubscriber.onSubscribe(FluxPeek.java:163)
        // at reactor.core.publisher.FluxSubscribeOn.subscribe(FluxSubscribeOn.java:66)
        // at reactor.core.publisher.FluxPeek.subscribe(FluxPeek.java:83)
        // at com.artshell.reactor.operators.FluxSubscribe.subscribeCoreSubscriber(FluxSubscribe.java:53)
        // at com.artshell.reactor.operators.FluxSubscribe.main(FluxSubscribe.java:83)
        // onSubscribe => Thread[main,5,main]
    }

    public static void main(String[] args) {
//        subscribeSubscriber();
        subscribeCoreSubscriber();
    }
}
