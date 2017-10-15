package com.artshell.reactor.operators;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;

import java.util.stream.Stream;

public class FluxSwitchOnNext {
    /**
     * @see reactor.core.publisher.Flux#switchOnNext(Publisher)
     * @see reactor.core.publisher.Flux#switchOnNext(Publisher, int)
     */
    public static void main(String[] args) {
        Flux.switchOnNext(Flux.fromStream(Stream.of(Flux.just("$", "A", "B", "9"), Flux.just("#"), Flux.just("@"), Flux.just("%"))))
                .subscribe(System.out::println);

        // obtain result:
        // $, A, B, 9, #, @, %
    }
}
