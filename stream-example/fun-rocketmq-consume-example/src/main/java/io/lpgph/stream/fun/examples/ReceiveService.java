package io.lpgph.stream.fun.examples;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Consumer;
import java.util.function.Function;

@Service
public class ReceiveService {

  @Bean
  public Function<Mono<String>, Mono<Void>> input1() {
    return mono ->
        mono.map(
                s -> {
                  System.out.println("input1 receive: " + s);
                  return s;
                })
            .then();
  }

  @Bean
  public Consumer<Mono<String>> input2() {
    return mono ->
        mono.subscribe(
            s -> {
              System.out.println("input2 receive: " + s);
            });
  }

  @Bean
  public Function<Mono<Foo>, Mono<Void>> input3() {
    return mono ->
        mono.map(
                s -> {
                  System.out.println("input3 receive: " + s);
                  return s;
                })
            .then();
  }

  @Bean
  public Function<Flux<String>, Flux<String>> input4() {
    return flux ->
        flux.map(
            s -> {
              String msg = "input4 receive  transaction msg:: " + s;
              System.out.println(msg);
              return msg;
            });
  }

  @Bean
  public Function<Flux<String>, Flux<String>> input5() {
    return flux ->
        flux.map(
            s -> {
              String msg = "pull msg: " + s;
              System.out.println(msg);
              return msg;
            });
  }
}
