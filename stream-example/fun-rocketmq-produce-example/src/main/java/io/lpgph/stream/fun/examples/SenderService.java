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

  //  @PollableBean
  //  public Supplier<Flux<String>> output2() {
  //    return () -> Flux.just("hello", "bye");
  //  }

  //  @Bean
  //  public Supplier<Flux<String>> output2() {
  //    return () ->
  //        Flux.fromStream(
  //                Stream.generate(
  //                        () -> {
  //                          try {
  //                            System.out.println("output2 sent event");
  //                            Thread.sleep(1000);
  //                            return "Hello from Supplier";
  //                          } catch (Exception e) {
  //                            // ignore
  //                          }
  //                          return "";
  //                        }))
  //            .subscribeOn(Schedulers.elastic())
  //            .share();
  //  }

  private final Sinks.Many<Message<?>> sink = Sinks.many().multicast().onBackpressureBuffer();

  @Bean
  public Supplier<Flux<Message<?>>> output2() {
    System.out.println("output2 sent event");
    return sink::asFlux;
  }

  public void sendOutput2(Message<?> event) {
    log.info("Event sent: " + event.toString());
    while (sink.tryEmitNext(event).isFailure()) {
      LockSupport.parkNanos(10);
    }
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
