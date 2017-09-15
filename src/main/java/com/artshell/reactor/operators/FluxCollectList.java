package com.artshell.reactor.operators;

import io.reactivex.Flowable;
import reactor.core.publisher.Flux;

/**
 * @see Flux#collectList() the same as {@link Flowable#toList()}
 */
public class FluxCollectList {
    public static void main(String[] args) {
        Flux.concat(Flux.range(1, 5), Flux.range(10, 15))
                .collectList()
                .subscribe(System.out::println);
        // obtain result:
        // [1, 2, 3, 4, 5, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24]
    }
}
