package io.lpgph.ddd.event;

import io.lpgph.ddd.event.model.EventStored;
import io.lpgph.ddd.event.model.EventStoredDao;
import io.lpgph.ddd.user.model.UserEvent;
import io.lpgph.ddd.utils.json.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
public class StatProcessor {

  private final EventStoredDao eventStoredDao;

  public StatProcessor(EventStoredDao eventStoredDao) {
    this.eventStoredDao = eventStoredDao;
  }

  @Async
  @TransactionalEventListener
  public void handleAfterPersonSavedComplete(UserEvent event) {
    log.info("\n\n\n\n事件监听  {}\n\n\n\n\n", JsonUtil.toJson(event));
    eventStoredDao.save(new EventStored(event));
  }
}
