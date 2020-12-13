package io.lpgph.stream.fun.examples;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.LockSupport;
import java.util.function.Supplier;
import java.util.stream.Stream;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.function.context.PollableBean;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Slf4j
@Service
public class SenderService {

  private final BlockingQueue<Message<?>> output1Event = new LinkedBlockingQueue<>();

  @Bean
  public Supplier<Message<?>> output1() {
    System.out.println("output1 sent event");
    return this.output1Event::poll;
  }

  public void sendOutput1(Message<?> event) {
    this.output1Event.offer(event);
    log.info("Event sent: " + event.toString());
  }

  private final BlockingQueue<Message<?>> output2Event = new LinkedBlockingQueue<>();

  @Bean
  public Supplier<Message<?>> output2() {
    System.out.println("output2 sent event");
    return this.output2Event::poll;
  }

  public void sendOutput2(Message<?> event) {
    log.info("Event sent: " + event.toString());
    this.output2Event.offer(event);
  }

  private final BlockingQueue<Message<?>> output3Event = new LinkedBlockingQueue<>();

  @Bean
  public Supplier<Message<?>> output3() {
    System.out.println("output3 sent event");
    return this.output3Event::poll;
  }

  public void sendOutput3(Message<?> event) {
    this.output3Event.offer(event);
    log.info("Event sent: " + event.toString());
  }
}
