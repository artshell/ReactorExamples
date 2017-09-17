package com.artshell.reactor.operators;


import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;

public class FluxConcatDelayError {

    /**
     * @see reactor.core.publisher.Flux#concatDelayError(Publisher[])
     * @see reactor.core.publisher.Flux#concatDelayError(Publisher)
     * @see reactor.core.publisher.Flux#concatDelayError(Publisher, int)
     */
    private static void delayErrorEnd() {
        Flux<Integer> errorElement = Flux.just(1).flatMap(i -> {
            if (i > 1) {
                return Flux.just(i);
            } else {
                return Flux.error(new IllegalStateException());
            }
        });

        Flux.concatDelayError(Flux.just(Flux.just(5), Flux.just(8), Flux.just(7), errorElement))
                .subscribe(System.out::println, Throwable::printStackTrace);

        // obtain result:
        // 5
        // 8
        // 7
        // java.lang.IllegalStateException
    }

    /**
     * @see reactor.core.publisher.Flux#concatDelayError(Publisher, boolean, int)
     */
    private static void delayErrorBoundary() {
        Flux<Integer> errorElement = Flux.just(9).flatMap(i -> {
            if (i > 10) {
                return Flux.just(i);
            } else {
                return Flux.error(new IllegalStateException());
            }
        });

        Flux.concatDelayError(Flux.just(Flux.just(5), errorElement, Flux.just(8), Flux.just(7)), false, 2)
                .subscribe(System.out::println, Throwable::printStackTrace);

        // obtain result:
        // 5
        // java.lang.IllegalStateException
    }

    public static void main(String[] args) {
//        delayErrorEnd();

        delayErrorBoundary();
    }

}
