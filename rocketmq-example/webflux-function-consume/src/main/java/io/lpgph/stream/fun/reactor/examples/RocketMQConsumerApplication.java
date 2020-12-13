package io.lpgph.stream.fun.reactor.examples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RocketMQConsumerApplication {

  public static void main(String[] args) {
    SpringApplication.run(RocketMQConsumerApplication.class, args);
  }


//  @Bean
//  public ConsumerCustomRunner customRunner() {
//    return new ConsumerCustomRunner();
//  }
//
//  public static class ConsumerCustomRunner implements CommandLineRunner {
//
//    @Autowired private ReceiveService receiveService;
//
//    @Override
//    public void run(String... args) throws InterruptedException {
//      while (true) {
//        receiveService.input5();
//        Thread.sleep(2_000);
//      }
//    }
//  }
}
