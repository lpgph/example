package io.lpgph.ddd;

import io.lpgph.ddd.event.model.EventStored;
import io.lpgph.ddd.user.model.UserEvent;
import io.lpgph.ddd.event.model.EventStoredRepository;
import io.lpgph.ddd.utils.json.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.Random;

@Slf4j
@Component
public class StatProcessor {

  private final EventStoredRepository eventStoredRepository;

  public StatProcessor(EventStoredRepository eventStoredRepository) {
    this.eventStoredRepository = eventStoredRepository;
  }

  @Async
  @TransactionalEventListener
  public void handleAfterPersonSavedComplete(UserEvent event) {
    log.info("\n\n\n\n事件监听  {}\n\n\n\n\n", JsonUtil.toJson(event));
    // 保存事件
    EventStored eventStored = eventStoredRepository.save(new EventStored(event));
    // 发送事件 try catch
    // 事件状态改为发送成功 否则是失败 则由定时任务 定时处理
    Random random = new Random(1000);
    try {
      if (random.nextInt(10) > 2) throw new RuntimeException("模拟消息队列失效");
      eventStored.changeSuccess();
    } catch (Exception e) {
      eventStored.changeFailed();
    }
    eventStoredRepository.save(eventStored);
  }
}
