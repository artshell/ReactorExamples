package com.artshell.reactor.operators;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

public class FluxDoXXXOperator {


    /**
     * @see Flux
     */
    private static void doXXX() {
        Flux.just(5)
                .doFinally(signalType -> System.out.println("doFinally"))
                .doAfterTerminate(() -> System.out.println("doAfterTerminate"))
                .doOnTerminate(() -> System.out.println("doOnTerminate"))
                .doOnRequest(c -> System.out.println("doOnRequest => " + c))
                .doOnNext(i -> System.out.println("doOnNex => " + i))
                .doOnComplete(() -> System.out.println("doOnComplete"))
                .doOnSubscribe(s -> System.out.println("doOnSubscribe"))
                .subscribe(System.out::println);

        // obtain result:
        // doOnSubscribe
        // doOnRequest => 9223372036854775807
        // doOnNex => 5
        // 5
        // doOnTerminate
        // doOnComplete
        // doAfterTerminate
        // doFinally
    }

    private static void doXXX2() {
        Flux.range(1, 5)
                .flatMap(i -> {
                    if (i == 2) {
                        return Flux.error(new IllegalStateException());
                    }
                    if (i == 4) {
                        return Flux.error(new IllegalArgumentException());
                    }
                    return Flux.just(i);
                })
                .doOnError(thr -> System.out.println("doOnError " + thr))
                .doOnError(IllegalArgumentException.class, thr -> System.out.println("doOnError class " + thr))
                .doOnError(thr -> thr instanceof IllegalStateException, thr -> System.out.println("doOnError predicate " + thr))
                .doOnCancel(() -> System.out.println("doOnCancel"))
                .doOnEach(sin -> {
                    if (sin.isOnNext()) {
                        System.out.println("doOnEach =>" + sin.get());
                    }

                    if (sin.isOnError()) {
                        System.out.println("doOnEach =>" + sin.getThrowable());
                    }
                })
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Integer.MAX_VALUE);
                    }

                    @Override
                    public void onNext(Integer i) {
                        System.out.println("subscribe => " + i);
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        // obtain result：
        // doOnEach =>1
        // subscribe => 1
        // doOnError java.lang.IllegalStateException
        // doOnError predicate java.lang.IllegalStateException
        // doOnEach =>java.lang.IllegalStateException
    }


    public static void main(String[] args) {
//        doXXX();

        doXXX2();
    }
}
