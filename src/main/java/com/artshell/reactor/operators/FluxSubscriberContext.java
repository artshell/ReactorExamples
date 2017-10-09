package com.artshell.reactor.operators;


import org.reactivestreams.Subscription;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Flux;
import reactor.util.context.Context;

import java.util.function.Function;

/**
 * @see reactor.core.publisher.Flux#subscriberContext(Context)
 * @see reactor.core.publisher.Flux#subscriberContext(Function)
 */
public class FluxSubscriberContext {

    private static void subscriberContext() {
        Flux.just(Context.of(1 + "$", 1))
                .subscriberContext(Context.of(7 + "$", 10))
                .subscribe(new CoreSubscriber<Context>() {
                    @Override
                    public Context currentContext() {
                        return Context.of("imp", 11);
                    }

                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Integer.MAX_VALUE);
                    }

                    @Override
                    public void onNext(Context context) {
                        System.out.println(context.toString());
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        // obtain result:
        // Context1{1$=1}
    }

    public static void main(String[] args) {
        subscriberContext();
    }
}
