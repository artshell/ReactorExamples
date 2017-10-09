package com.artshell.reactor.operators;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Flux;

public class FluxSubscribeWith {
    /**
     * @see Flux#subscribeWith(Subscriber)
     */
    public static void main(String[] args) {
        CoreSubscriber<String> sub = Flux.just("$")
                .subscribeWith(new CoreSubscriber<String>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(1);
                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println(s);
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        // obtain result:
        // $
    }
}
