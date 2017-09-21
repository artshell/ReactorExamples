package com.artshell.reactor.operators;


import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

public class FluxSubscribeOn {

    /**
     * @see reactor.core.publisher.Flux#subscribeOn(Scheduler)
     */
    private static void subscribeOn() {
        Flux.range(3, 5)
                .subscribeOn(Schedulers.newElastic("subscribeOn"))
                .doOnSubscribe(s -> System.out.println("doOnSubscribe => " + Thread.currentThread()))
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(1);
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
        // onNext => 3 Thread[subscribeOn-2,5,main]
    }

    /**
     * @see reactor.core.publisher.Flux#subscribeOn(Scheduler, boolean)
     */
    private static void subscribeOnRequest() {
        Flux.range(8, 2)
                .subscribeOn(Schedulers.newElastic("subscribeOnRequest"), false)
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
        // onNext => 8 Thread[subscribeOnRequest-2,5,main]
    }

    public static void main(String[] args) {
//        subscribeOn();
        subscribeOnRequest();
    }
}
