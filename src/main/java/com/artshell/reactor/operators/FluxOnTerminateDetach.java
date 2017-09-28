package com.artshell.reactor.operators;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

public class FluxOnTerminateDetach {

    /**
     * @see Flux#onTerminateDetach()
     */
    public static void main(String[] args) {
        Flux.range(1, 100)
                .onTerminateDetach()
                .subscribe(new Subscriber<Integer>() {
                    Subscription s;
                    @Override
                    public void onSubscribe(Subscription s) {
                        this.s = s;
                        s.request(Integer.MAX_VALUE);
                        System.out.println("onSubscribe");
                    }

                    @Override
                    public void onNext(Integer v) {
                        System.out.println(v);
                        if (v == 10) {
                            s.cancel();
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        t.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete");
                    }
                });

        // obtain result:
        // onSubscribe
        // 1, 2, 3, 4, 5, 6, 7, 8, 9, 10
    }
}
