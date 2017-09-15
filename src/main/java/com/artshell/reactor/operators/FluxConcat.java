package com.artshell.reactor.operators;


import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;

/**
 * @see reactor.core.publisher.Flux#concat(Publisher, int)
 */
public class FluxConcat {

    public static void main(String[] args) {
        Flux<Flux<Integer>> array = Flux.just(Flux.just(1), Flux.just(3), Flux.just(8), Flux.just(5), Flux.just(6), Flux.just(10), Flux.just(21));

        Flux.concat(array,2)
                .subscribe(System.out::println);
        // obtain result:
        // 1
        // 3
        // 8
        // 5
        // 6
        // 10
        // 21
    }
}
