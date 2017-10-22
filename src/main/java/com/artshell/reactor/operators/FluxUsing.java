package com.artshell.reactor.operators;

import reactor.core.Disposable;
import reactor.core.publisher.Flux;

import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Function;

public class FluxUsing {

    /**
     * @see reactor.core.publisher.Flux#using(Callable, Function, Consumer)
     * @see reactor.core.publisher.Flux#using(Callable, Function, Consumer, boolean)
     *
     */
    public static void main(String[] args) {
        Flux.using(() -> (Disposable) () -> System.out.println("disposed"), d -> Flux.range(1, 5), Disposable::dispose)
                .subscribe(System.out::println);

        // obtain result:
        // 1, 2, 3, 4, 5
        // disposed
    }
}
