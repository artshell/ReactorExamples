package com.artshell.reactor.operators;

import reactor.core.publisher.Flux;

/**
 * @see Flux#checkpoint()
 * @see Flux#checkpoint(String, boolean)
 */
public class FluxCheckpoint {

    public static void main(String[] args) {
        Flux<Integer> check = Flux.just(2)
                .flatMap(i -> {
                    if (i == 2) {
                        return Flux.error(new IllegalStateException());
                    } else {
                        return Flux.just(i);
                    }
                });

        check.checkpoint("FluxCheckpoint", false).subscribe(System.out::println, Throwable::printStackTrace);
        // obtain result:
/*        java.lang.IllegalStateException
        at com.artshell.reactor.operators.FluxCheckpoint.lambda$main$0(FluxCheckpoint.java:15)
        at reactor.core.publisher.FluxFlatMap.trySubscribeScalarMap(FluxFlatMap.java:141)
        at reactor.core.publisher.FluxFlatMap.subscribe(FluxFlatMap.java:93)
        at reactor.core.publisher.FluxOnAssembly.subscribe(FluxOnAssembly.java:252)
        at reactor.core.publisher.Flux.subscribe(Flux.java:6293)
        at reactor.core.publisher.Flux.subscribeWith(Flux.java:6472)
        at reactor.core.publisher.Flux.subscribe(Flux.java:6286)
        at reactor.core.publisher.Flux.subscribe(Flux.java:6250)
        at reactor.core.publisher.Flux.subscribe(Flux.java:6220)
        at com.artshell.reactor.operators.FluxCheckpoint.main(FluxCheckpoint.java:21)
        Suppressed: reactor.core.publisher.FluxOnAssembly$OnAssemblyException:
        Assembly site of producer [reactor.core.publisher.FluxFlatMap] is identified by light checkpoint [FluxCheckpoint]."description" : "FluxCheckpoint"*/


        check.checkpoint("FluxCheckpoint", true).subscribe(System.out::println, Throwable::printStackTrace);

        // obtain result:
/*        java.lang.IllegalStateException
        at com.artshell.reactor.operators.FluxCheckpoint.lambda$main$0(FluxCheckpoint.java:15)
        at reactor.core.publisher.FluxFlatMap.trySubscribeScalarMap(FluxFlatMap.java:141)
        at reactor.core.publisher.FluxFlatMap.subscribe(FluxFlatMap.java:93)
        at reactor.core.publisher.FluxOnAssembly.subscribe(FluxOnAssembly.java:252)
        at reactor.core.publisher.Flux.subscribe(Flux.java:6293)
        at reactor.core.publisher.Flux.subscribeWith(Flux.java:6472)
        at reactor.core.publisher.Flux.subscribe(Flux.java:6286)
        at reactor.core.publisher.Flux.subscribe(Flux.java:6250)
        at reactor.core.publisher.Flux.subscribe(Flux.java:6220)
        at com.artshell.reactor.operators.FluxCheckpoint.main(FluxCheckpoint.java:25)
        Suppressed: reactor.core.publisher.FluxOnAssembly$OnAssemblyException:
        Assembly trace from producer [reactor.core.publisher.FluxFlatMap], described as [FluxCheckpoint] :
        reactor.core.publisher.Flux.checkpoint(Flux.java:2586)
        com.artshell.reactor.operators.FluxCheckpoint.main(FluxCheckpoint.java:25)
        Error has been observed by the following operator(s):
	|_	Flux.checkpoint(FluxCheckpoint.java:25)*/
    }
}
