package io.lpgph.stream.fun.examples;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.support.MessageBuilder;

import java.util.UUID;

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

  public static class CustomRunner implements CommandLineRunner {

    private final String bindingName;

    public CustomRunner(String bindingName) {
      this.bindingName = bindingName;
    }

    @Autowired private SenderService senderService;
    @Autowired private MessageService messageService;

    @Override
    public void run(String... args) throws Exception {
      if (this.bindingName.equals("output1")) {
        int count = 5;
        for (int index = 1; index <= count; index++) {
          String msgContent = "fun-msg-" + index;
          if (index % 3 == 0) {
            senderService.sendOutput1(messageService.send(msgContent));
          } else if (index % 3 == 1) {
            senderService.sendOutput1(messageService.sendWithTags(msgContent, "tagStr"));
          } else {
            senderService.sendOutput1(
                    messageService.sendObject(new Foo(index, "fun-foo"), "tagObj"));
          }
        }
      } else if (this.bindingName.equals("output3")) {
        int count = 20;
        for (int index = 1; index <= count; index++) {
          String msgContent = "fun-pullMsg-" + index;
          senderService.sendOutput3(MessageBuilder.withPayload(msgContent).build());
        }
      }
    }
  }

  public static class CustomRunnerWithTransactional implements CommandLineRunner {

    @Autowired private SenderService senderService;
    @Autowired private MessageService messageService;

    @Override
    public void run(String... args) throws Exception {
      // COMMIT_MESSAGE message
      senderService.sendOutput2(messageService.sendTransactionalMsg("fun-transactional-msg1", 1));
      // ROLLBACK_MESSAGE message
      senderService.sendOutput2(messageService.sendTransactionalMsg("fun-transactional-msg2", 2));
      // ROLLBACK_MESSAGE message
      senderService.sendOutput2(messageService.sendTransactionalMsg("fun-transactional-msg3", 3));
      // COMMIT_MESSAGE message
      senderService.sendOutput2(messageService.sendTransactionalMsg("fun-transactional-msg4", 4));
    }
  }

  @Bean
  public Consumer2CustomRunner custom2Runner() {
    return new Consumer2CustomRunner();
  }

  public static class Consumer2CustomRunner implements CommandLineRunner {

    @Autowired private SenderService senderService;
    @Autowired private MessageService messageService;

    @Override
    public void run(String... args) throws InterruptedException {
      while (true) {
        senderService.sendOutput2(messageService.send(UUID.randomUUID().toString()));
        Thread.sleep(2_000);
      }
    }
  }
}
