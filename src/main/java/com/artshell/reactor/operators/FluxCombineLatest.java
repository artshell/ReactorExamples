package com.artshell.reactor.operators;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class FluxCombineLatest {

    /**
     * @see reactor.core.publisher.Flux#combineLatest(Function, Publisher[])
     * @see reactor.core.publisher.Flux#combineLatest(Function, int, Publisher[])
     *
     * {@link Stream#allMatch(Predicate)}
     * {@link Stream#anyMatch(Predicate)}
     * {@link Stream#noneMatch(Predicate)}
     */
    private static void combineLatestFunc() {

        Flux.combineLatest(obj -> Stream.of(obj).allMatch(o -> (Boolean) o), Flux.just(true), Flux.just(true), Flux.just(true))
                .subscribe(System.out::println);
        // obtain result:
        // true


        Flux.combineLatest(obj -> Stream.of(obj).allMatch(o -> (Boolean) o), Flux.just(false), Flux.just(false), Flux.just(true))
                .subscribe(System.out::println);
        // obtain result:
        // false

        Flux.combineLatest(obj -> obj, Flux.just(1), Flux.just(2), Flux.just(3), Flux.just(4))
                .flatMap(Flux::fromArray)
                .all(o -> (int)o < 5)
                .subscribe(System.out::println);
        // obtain result:
        // true

        Flux.combineLatest(obj -> Stream.of(obj).allMatch(o -> (Boolean) o))
                .subscribe(System.out::println);
        Flux.combineLatest(obj -> Stream.of(obj).allMatch(o -> (Boolean) o), Flux.empty(), Flux.empty(), Flux.empty())
                .subscribe(System.out::println);
        // obtain result:
        // no result


        // ===========================================
        Flux.combineLatest(obj -> Stream.of(obj).anyMatch(o -> (Boolean) o), Flux.just(false), Flux.just(true), Flux.just(true))
                .subscribe(System.out::println);
        // obtain result:
        // true

        Flux.combineLatest(obj -> Stream.of(obj).anyMatch(o -> (Boolean) o))
                .subscribe(System.out::println);
        // obtain result:
        // no result


        // ===========================================
        Flux.combineLatest(obj -> Stream.of(obj).noneMatch(o -> (Boolean) o))
                .subscribe(System.out::println);
        Flux.combineLatest(obj -> Stream.of(obj).noneMatch(o -> (Boolean) o), Flux.empty(), Flux.empty(), Flux.empty())
                .subscribe(System.out::println);
        // obtain same result:
        // no result

        Flux.combineLatest(obj -> Stream.of(obj).noneMatch(o -> ((int)o) > 5), Flux.just(1), Flux.just(2), Flux.just(3), Flux.just(4))
                .subscribe(System.out::println);
        // obtain result:
        // true

        Flux.combineLatest(obj -> Stream.of(obj).noneMatch(o -> ((int)o) < 3), Flux.just(1), Flux.just(2))
                .subscribe(System.out::println);
        // obtain result:
        // false
    }

    /**
     * @see Flux#combineLatest(Iterable, Function)
     */
    private static void combineLatestIter() {
        List<Publisher<String>> iter = new ArrayList<>();
        iter.add(Flux.just("r"));
        iter.add(Flux.just("e"));
        iter.add(Flux.just("a"));
        iter.add(Flux.just("c"));
        iter.add(Flux.just("t"));
        iter.add(Flux.just("o"));
        iter.add(Flux.just("r"));

        Flux.combineLatest(iter, obs -> Stream.of(obs).reduce((l, r) -> (String)l + r))
                .map(Optional::get)
                .subscribe(System.out::println);
        // obtain result:
        // reactor
    }

    public static void main(String[] args) {
//        combineLatestFunc();
//        combineLatestIter();


    }
}
