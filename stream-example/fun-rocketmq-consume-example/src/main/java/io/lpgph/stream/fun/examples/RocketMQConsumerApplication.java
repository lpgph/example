package io.lpgph.stream.fun.examples;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RocketMQConsumerApplication {

  public static void main(String[] args) {
    SpringApplication.run(RocketMQConsumerApplication.class, args);
  }

  @Bean
  public ConsumerCustomRunner customRunner() {
    return new ConsumerCustomRunner();
  }

  public static class ConsumerCustomRunner implements CommandLineRunner {

    @Autowired private ReceiveService receiveService;

    @Override
    public void run(String... args) throws InterruptedException {
      while (true) {
        receiveService.input5();
        Thread.sleep(2_000);
      }
    }
  }
}
