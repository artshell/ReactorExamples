package com.artshell.reactor.operators;

import reactor.core.publisher.Flux;

import java.util.function.Function;

public class FluxGroupBy {

    /**
     * @see Flux#groupBy(Function)
     * @param args
     */
    public static void main(String[] args) {
        Flux.just("k=8","o=9","$=*", "o=1","y=p","k=8","y=%")
                .groupBy(s -> s.split("=")[0])
                .subscribe(g -> System.out.println(g.key()));
        // obtain result:
        // k
        // o
        // $
        // y
    }
}
