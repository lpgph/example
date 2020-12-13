package io.lpgph.stream.fun.reactor.examples;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
public class ReceiveService {

  @Bean
  public Function<Flux<String>, Mono<Void>> input1() {
    return flux ->
        flux.map(
                s -> {
                  System.out.println("input1 receive: " + s);
                  return s;
                })
            .then();
  }

  @Bean
  public Function<Flux<String>, Mono<Void>> input2() {
    return flux ->
        flux.map(
                s -> {
                  System.out.println("input2 receive: " + s);
                  return s;
                })
            .then();
  }

  @Bean
  public Function<Flux<Foo>, Mono<Void>> input3() {
    return flux ->
        flux.map(
                s -> {
                  System.out.println("input3 receive: " + s);
                  return s;
                })
            .then();
  }

  @Bean
  public Function<Flux<String>, Mono<Void>> input4() {
    return flux ->
        flux.map(
                s -> {
                  String msg = "input4 receive  transaction msg:: " + s;
                  System.out.println(msg);
                  return msg;
                })
            .then();
  }

  @Bean
  public Function<Flux<String>, Mono<Void>> input5() {
    return flux ->
        flux.map(
                s -> {
                  String msg = "pull msg: " + s;
                  System.out.println(msg);
                  return msg;
                })
            .then();
  }
}
