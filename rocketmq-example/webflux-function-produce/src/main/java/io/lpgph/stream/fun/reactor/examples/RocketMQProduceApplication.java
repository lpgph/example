package io.lpgph.stream.fun.reactor.examples;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.function.context.PollableBean;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.UUID;
import java.util.concurrent.locks.LockSupport;
import java.util.function.Supplier;

@Slf4j
@SpringBootApplication
public class RocketMQProduceApplication {

  public static void main(String[] args) {
    SpringApplication.run(RocketMQProduceApplication.class, args);
  }

  @Bean
  public CustomRunner customRunner() {
    return new CustomRunner("output1");
  }

  @Bean
  public CustomRunner customRunner2() {
    return new CustomRunner("output3");
  }

  @Bean
  public CustomRunnerWithTransactional customRunnerWithTransactional() {
    return new CustomRunnerWithTransactional();
  }

  public class CustomRunner implements CommandLineRunner {

    private final String bindingName;

    public CustomRunner(String bindingName) {
      this.bindingName = bindingName;
    }

    @Autowired private MessageService messageService;

    @Override
    public void run(String... args) throws Exception {
      if (this.bindingName.equals("output1")) {
        int count = 5;
        for (int index = 1; index <= count; index++) {
          String msgContent = "fun-msg-" + index;
          if (index % 3 == 0) {
            sendOutput1(messageService.send(msgContent));
          } else if (index % 3 == 1) {
            sendOutput1(messageService.sendWithTags(msgContent, "tagStr"));
          } else {
            sendOutput1(messageService.sendObject(new Foo(index, "fun-foo"), "tagObj"));
          }
        }
      } else if (this.bindingName.equals("output3")) {
        int count = 20;
        for (int index = 1; index <= count; index++) {
          String msgContent = "fun-pullMsg-" + index;
          sendOutput3(MessageBuilder.withPayload(msgContent).build());
        }
      }
    }
  }

  public class CustomRunnerWithTransactional implements CommandLineRunner {

    @Autowired private MessageService messageService;

    @Override
    public void run(String... args) throws Exception {
      // COMMIT_MESSAGE message
      sendOutput2(messageService.sendTransactionalMsg("fun-transactional-msg1", 1));
      // ROLLBACK_MESSAGE message
      sendOutput2(messageService.sendTransactionalMsg("fun-transactional-msg2", 2));
      // ROLLBACK_MESSAGE message
      sendOutput2(messageService.sendTransactionalMsg("fun-transactional-msg3", 3));
      // COMMIT_MESSAGE message
      sendOutput2(messageService.sendTransactionalMsg("fun-transactional-msg4", 4));
    }
  }

  private final Sinks.Many<Message<?>> output1Event =
      Sinks.many().multicast().onBackpressureBuffer();

  @Bean
  public Supplier<Flux<Message<?>>> output1() {
    System.out.println("output1 sent event");
    return this.output1Event::asFlux;
  }

  public void sendOutput1(Message<?> event) {
    while (this.output1Event.tryEmitNext(event).isFailure()) {
      LockSupport.parkNanos(10);
    }
    log.info("Event sent: " + event.toString());
  }



  private final Sinks.Many<Message<?>> output2Event =
      Sinks.many().multicast().onBackpressureBuffer();

  @Bean
  public Supplier<Flux<Message<?>>> output2() {
    System.out.println("output2 sent event");
    return this.output2Event::asFlux;
  }

  public void sendOutput2(Message<?> event) {
    log.info("Event sent: " + event.toString());
    while (this.output2Event.tryEmitNext(event).isFailure()) {
      LockSupport.parkNanos(10);
    }
  }

  private final Sinks.Many<Message<?>> output3Event =
      Sinks.many().multicast().onBackpressureBuffer();

  @Bean
  public Supplier<Flux<Message<?>>> output3() {
    System.out.println("output3 sent event");
    return this.output3Event::asFlux;
  }

  public void sendOutput3(Message<?> event) {
    log.info("Event sent: " + event.toString());
    while (this.output3Event.tryEmitNext(event).isFailure()) {
      LockSupport.parkNanos(10);
    }
  }

  //  @Bean
  //  public Supplier<Flux<String>> output4() {
  //    return () ->
  //        Flux.fromStream(
  //                Stream.generate(
  //                    () -> {
  //                      try {
  //                        Thread.sleep(1000);
  //                        String msg = "Hello from Supplier  " + UUID.randomUUID().toString();
  //                        log.info(msg);
  //                        return msg;
  //                      } catch (Exception e) {
  //                        // ignore
  //                      }
  //                      return "";
  //                    }))
  //            .subscribeOn(Schedulers.boundedElastic())
  //            .share();
  //  }

  @PollableBean
  public Supplier<Flux<String>> output4() {
      return () ->{
        String msg = "Hello from Supplier  " + UUID.randomUUID().toString();
        log.info(msg);
        return Flux.just(msg);
      };
    }
}
