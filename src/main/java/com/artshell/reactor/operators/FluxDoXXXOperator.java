package com.artshell.reactor.operators;


import reactor.core.publisher.Flux;

/**
 * @see reactor.core.publisher.Flux#doAfterTerminate(Runnable)
 */
public class FluxDoXXXOperator {

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


    public static void main(String[] args) {
        doXXX();

    }
}
