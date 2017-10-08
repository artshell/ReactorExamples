package com.artshell.reactor.operators;

import reactor.core.publisher.Flux;

import java.util.function.Function;

/**
 * @see reactor.core.publisher.Flux#publish(Function)
 * @see reactor.core.publisher.Flux#publish(Function, int)
 */
public class FluxPublish2 {
    public static void main(String[] args) {
        Flux<String> pub = Flux.range(4, 3).publish(fun -> fun.map(i -> i + "$"));

        // first consumer
        pub.doOnSubscribe(s -> System.out.println("First sub start => ")).subscribe(System.out::println);
        // obtain result:
        // First sub start =>
        // 4$,5$,6$


        // second consumer
        pub.doOnSubscribe(s -> System.out.println("Second sub start => ")).subscribe(System.out::println);
        // obtain result:
        // Second sub start =>
        // 4$,5$,6$
    }
}
