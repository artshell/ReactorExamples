package com.artshell.reactor.operators;

import reactor.core.publisher.Flux;

import java.util.function.Function;
import java.util.function.Predicate;

public class FluxOnErrorResume {

    /**
     * @see Flux#onErrorResume(Class, Function)
     * @see Flux#onErrorResume(Predicate, Function)
     */
    private static void onErrorResumeClz() {
        Flux.range(1, 3)
                .flatMap(i -> {
                    if (i == 2) {
                        return Flux.error(new IllegalStateException());
                    }
                    return Flux.just(i);
                })
                .onErrorResume(thr -> thr instanceof IllegalStateException, t -> Flux.just(9))
                .subscribe(System.out::println);
        // obtain result:
        // 1, 9
    }

    /**
     * @see Flux#onErrorResume(Function)
     */
    private static void onErrorResumeFun() {
        Flux.range(4, 3)
                .flatMap(i -> {
                    if (i == 5) {
                        return Flux.error(new IllegalStateException());
                    }
                    return Flux.just(i);
                })
                .onErrorResume(thr -> Flux.just(8))
                .subscribe(System.out::println);
        // obtain result:
        // 4, 8
    }

    public static void main(String[] args) {
//        onErrorResumeClz();
        onErrorResumeFun();
    }
}
