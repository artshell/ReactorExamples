package com.artshell.reactor.operators;

import reactor.core.publisher.Flux;

import java.util.function.Predicate;

public class FluxOnErrorReturn {

    /**
     * @see Flux#onErrorReturn(Object)
     */
    private static void onErrorReturnDefault() {
        Flux.range(5, 3)
                .flatMap(i -> {
                    if (i == 6) {
                        return Flux.error(new IllegalStateException());
                    }
                    return Flux.just(i);
                })
                .onErrorReturn(2)
                .subscribe(System.out::println, Throwable::printStackTrace);

        // obtain result:
        // 5, 2
    }

    /**
     * @see Flux#onErrorReturn(Class, Object)
     */
    private static void onErrorReturnClz() {
        Flux.just("A", "B", "G", "K")
                .flatMap(i -> {
                    if ("G".equals(i)) {
                        return Flux.error(new IllegalStateException());
                    }
                    return Flux.just(i);
                })
                .onErrorReturn(IllegalStateException.class, "D")
                .subscribe(System.out::println);

        // obtain result:
        // A, B, D
    }

    /**
     * @see Flux#onErrorReturn(Predicate, Object)
     */
    private static void onErrorRetrunPredicate() {
        Flux.just("G", "K", "Y", "S")
                .flatMap(i -> {
                    if ("Y".equals(i)) {
                        return Flux.error(new IllegalStateException());
                    }
                    return Flux.just(i);
                })
                .onErrorReturn(thr -> thr instanceof IllegalStateException, "F")
                .subscribe(System.out::println);

        // obtain result:
        // G, K, F
    }

    public static void main(String[] args) {
//        onErrorReturnDefault();
//        onErrorReturnClz();

        onErrorRetrunPredicate();
    }
}
