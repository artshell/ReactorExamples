package com.artshell.reactor.operators;

import reactor.core.publisher.Flux;

import java.util.function.Function;
import java.util.function.Predicate;

public class FluxOnErrorMap {

    /**
     * @see Flux#onErrorMap(Class, Function)
     * @see Flux#onErrorMap(Predicate, Function)
     */
    private static void onErrorMapClz() {
        Flux.range(2, 3)
                .flatMap(i -> {
                    if (i == 3) {
                        return Flux.error(new IllegalStateException());
                    }
                    return Flux.just(i);
                })
                .onErrorMap(IllegalStateException.class, ill -> ill)
                .subscribe(System.out::println, Throwable::printStackTrace);

        // obtain result:
        // 2
        // java.lang.IllegalStateException
    }

    /**
     * @see Flux#onErrorMap(Function)
     */
    private static void onErrorMapFun() {
        Flux.range(4, 9)
                .flatMap(i -> {
                    if (i == 6) {
                        return Flux.error(new IllegalStateException());
                    }
                    return Flux.just(i);
                })
                .onErrorMap(ill -> ill)
                .subscribe(System.out::println, Throwable::printStackTrace);

        // obtain result:
        // 4, 5
        // java.lang.IllegalStateException
    }

    public static void main(String[] args) {
//        onErrorMapClz();
        onErrorMapFun();
    }
}
