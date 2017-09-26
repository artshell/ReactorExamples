package com.artshell.reactor.operators;

import org.reactivestreams.Subscription;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Flux;

public class FluxIgnoreElements {

    /**
     * @see Flux#ignoreElements()
     */
    public static void main(String[] args) {
        Flux.range(1, 3)
                .ignoreElements()
                .subscribe(new CoreSubscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {

                    }

                    @Override
                    public void onNext(Integer i) {
                        System.out.println(i);
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete");
                    }
                });

        // obtain result:
        // onComplete
    }
}
