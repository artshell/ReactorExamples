package com.artshell.reactor.operators;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class FluxZip {
    /**
     * @see reactor.core.publisher.Flux#zip(Publisher, Publisher, Publisher)
     * @see reactor.core.publisher.Flux#zip(Publisher, Publisher, Publisher, Publisher)
     * @see reactor.core.publisher.Flux#zip(Publisher, Publisher, Publisher, Publisher, Publisher)
     * @see reactor.core.publisher.Flux#zip(Publisher, Publisher, Publisher, Publisher, Publisher, Publisher)
     * @see reactor.core.publisher.Flux#zip(Function, Publisher[])
     * @see reactor.core.publisher.Flux#zip(Function, int, Publisher[])
     */
    private static void zipFun() {
        Flux.zip(arry -> (String) arry[0] + arry[1] + arry[2], Flux.just("#"), Flux.just(2), Flux.just("$"))
                .subscribe(System.out::println);

        // obtain result:
        // #2$
    }

    /**
     * @see reactor.core.publisher.Flux#zip(Iterable, Function)
     * @see reactor.core.publisher.Flux#zip(Iterable, int, Function)
     */
    private static void zipIter() {
        List<Flux<Object>> arry = new ArrayList<>();
        arry.add(Flux.just(2));
        arry.add(Flux.just("$"));
        arry.add(Flux.just("%"));

        Flux.zip(arry, obs -> obs.length > 0 ? Arrays.toString(obs) : "")
                .subscribe(System.out::println);

        // obtain result:
        // [2, $, %]
    }

    /**
     * @see reactor.core.publisher.Flux#zip(Publisher, Function)
     */
    private static void zipPubFun() {
        Flux.zip(Flux.just(Flux.just("#"), Flux.just("-"), Flux.just("%")), (Function<Tuple2, String>) Tuple2::toString)
                .subscribe(System.out::println);

        // obtain result:
        // [#,-,%]
    }

    /**
     * @see reactor.core.publisher.Flux#zip(Publisher, Publisher)
     * @see reactor.core.publisher.Flux#zip(Publisher, Publisher, BiFunction)
     */
    private static void zipPubTuple2() {
        Flux.zip(Flux.just("$"), Flux.just(5), (l, r) -> l + r)
                .subscribe(System.out::println);

        // obtain result:
        // $5
    }

    public static void main(String[] args) {
//        zipFun();
//        zipIter();
//        zipPubFun();
        zipPubTuple2();
    }
}
