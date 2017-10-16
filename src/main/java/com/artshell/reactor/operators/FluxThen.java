package com.artshell.reactor.operators;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FluxThen {
    /**
     * @see Flux#then()
     */
    private static void then() {
        Flux.just("#")
                .then()
                .subscribe(v -> System.out.println("consumer"), Throwable::printStackTrace, () -> System.out.println("complete"));
        // obtain result:
        // complete
    }

    /**
     * @see Flux#then(Mono)
     */
    private static void thenMono() {
        Flux.just("%")
                .then(Mono.just("then mono"))
                .subscribe(System.out::println);

        // obtain result:
        // then mono
    }

    public static void main(String[] args) {
//        then();
        thenMono();
    }
}
