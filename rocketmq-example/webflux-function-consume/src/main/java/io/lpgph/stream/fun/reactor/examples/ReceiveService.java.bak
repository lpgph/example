package io.lpgph.stream.fun.examples;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Configuration
public class ReceiveService {

  @Bean
  public Consumer<String> input1() {
    return str -> System.out.println("input1 receive: " + str);
  }

  @Bean
  public Consumer<String> input2() {
    return s -> System.out.println("input2 receive: " + s);
  }

  @Bean
  public Consumer<Foo> input3() {
    return s -> System.out.println("input3 receive: " + s);
  }

  @Bean
  public Consumer<String> input4() {
    return s -> System.out.println("input4 receive  transaction msg:: " + s);
  }

  @Bean
  public Consumer<String> input5() {
    return s -> System.out.println("pull msg: " + s);
  }
}
