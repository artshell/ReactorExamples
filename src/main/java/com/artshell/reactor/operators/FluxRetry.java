package com.artshell.reactor.operators;

import reactor.core.publisher.Flux;

import java.util.function.Function;
import java.util.function.Predicate;

public class FluxRetry {
    /**
     * @see Flux#retry()
     * @see Flux#retry(long)
     */
    private static void retry() {
        Flux.concat(Flux.just("$"), Flux.error(new IllegalStateException()))
                .retry(1)
                .subscribe(System.out::println);
        // obtain result:
        // $
        // $
        // Exception in thread "main" reactor.core.Exceptions$ErrorCallbackNotImplemented: java.lang.IllegalStateException
    }

    /**
     * @see Flux#retry(Predicate)
     */
    private static void retryPredicate() {
        Flux.concat(Flux.just("#"), Flux.error(new IllegalStateException()))
                .retry(IllegalStateException.class::isInstance)
                .subscribe(System.out::println);
        // obtain result:
        // #, #, #, # ...
    }

    /**
     * @see Flux#retry(long, Predicate)
     */
    private static void retryCountPredicate() {
        Flux.concat(Flux.just("@"), Flux.error(new IllegalStateException()))
                .retry(2, e -> e instanceof IllegalStateException)
                .subscribe(System.out::println);
        // obtain result:
        // @
        // @
        // @
        // Exception in thread "main" reactor.core.Exceptions$ErrorCallbackNotImplemented: java.lang.IllegalStateException
    }

    /**
     * @see Flux#retryWhen(Function)
     */
    private static void retryWhen() {
        Flux.concat(Flux.just("%"), Flux.error(new IllegalStateException()))
                .retryWhen(th -> th.flatMap(thr -> thr instanceof IllegalStateException ? Flux.just(true) : Flux.error(thr)))
                .subscribe(System.out::println);
        // obtain result:
        // %, %, %, %, % ...
    }

    public static void main(String[] args) {
//        retry();
//        retryPredicate();
//        retryCountPredicate();
        retryWhen();
    }
}
