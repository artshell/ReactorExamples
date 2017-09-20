package com.artshell.reactor.operators;

import reactor.core.publisher.Flux;

public class FluxMaterialize {

    /**
     * @see Flux#materialize()
     */
    private static void materialize() {
        Flux.range(1, 3)
                .flatMap(i -> {
                    if (i == 2) {
                        return Flux.error(new IllegalStateException());
                    }
                    return Flux.just(i);
                })
                .materialize()
                .subscribe(s -> {
                    if (s.isOnNext()) {
                        System.out.println(s.get() + "$");
                    }

                    if (s.isOnError()) {
                        System.out.println(s.getThrowable());
                    }
                });

        // obtain result:
        // 1$
        // java.lang.IllegalStateException
    }

    /**
     * @see Flux#dematerialize()
     */
    private static void dematerialize() {
        Flux.range(1, 3)
                .flatMap(i -> {
                    if (i == 2) {
                        return Flux.error(new IllegalStateException());
                    }
                    return Flux.just(i + "#");
                })
                .materialize()
                .<String>dematerialize()
                .subscribe(System.out::println, Throwable::printStackTrace);

        // obtain result:
        // 1#
        // java.lang.IllegalStateException
        // at com.artshell.reactor.operators.FluxMaterialize.lambda$dematerialize$2(FluxMaterialize.java:41)
        // at reactor.core.publisher.FluxFlatMap$FlatMapMain.onNext(FluxFlatMap.java:353)
    }

    public static void main(String[] args) {
//        materialize();
        dematerialize();
    }
}
