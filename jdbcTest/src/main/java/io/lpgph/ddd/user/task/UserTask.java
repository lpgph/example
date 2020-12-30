package io.lpgph.ddd.user.task;

import io.lpgph.ddd.event.model.EventStoredRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class UserTask {

  private final EventStoredRepository eventStoredRepository;

  @Scheduled(cron = "*/1 * * * * ?")
  public void po() {
    System.out.println(System.currentTimeMillis());
  }

  @Scheduled(cron = "*/2 * * * * ?")
  public void event() {
    Random random = new Random(1000);

    eventStoredRepository.saveAll(
        eventStoredRepository.findAllByStatus(0, 2).stream()
            .peek(
                item -> {
                  try {
                    if (random.nextInt(10) > 2) throw new RuntimeException("模拟消息队列失效");
                    item.changeSuccess();
                  } catch (Exception e) {
                    item.changeFailed();
                  }
                })
            .collect(Collectors.toSet()));
  }
}
