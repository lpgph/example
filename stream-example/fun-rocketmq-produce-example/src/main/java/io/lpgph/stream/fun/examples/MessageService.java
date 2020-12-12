package io.lpgph.stream.fun.examples;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageConst;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
public class MessageService {

  public Message<String> send(String msg) {
    return MessageBuilder.withPayload(msg).build();
  }

  public <T> Message<T> sendWithTags(T msg, String tag) {
    return MessageBuilder.createMessage(
        msg,
        new MessageHeaders(
            Stream.of(tag)
                .collect(Collectors.toMap(str -> MessageConst.PROPERTY_TAGS, String::toString))));
  }

  public <T> Message<T> sendObject(T msg, String tag) {
    return MessageBuilder.withPayload(msg)
        .setHeader(MessageConst.PROPERTY_TAGS, tag)
        .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
        .build();
  }

  public <T> Message<T> sendTransactionalMsg(T msg, int num) {
    MessageBuilder<T> builder =
        MessageBuilder.withPayload(msg)
            .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON);
    builder.setHeader("test", String.valueOf(num));
    builder.setHeader(RocketMQHeaders.TAGS, "binder");
    return builder.build();
  }
}
