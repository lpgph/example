package io.lpgph.ddd;

import io.lpgph.ddd.event.model.EventStored;
import io.lpgph.ddd.user.model.UserEvent;
import io.lpgph.ddd.event.model.EventStoredRepository;
import io.lpgph.ddd.utils.json.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

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
    eventStoredRepository.save(new EventStored(event));
  }
}
