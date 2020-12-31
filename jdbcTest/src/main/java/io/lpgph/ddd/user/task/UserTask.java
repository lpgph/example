package io.lpgph.ddd.user.task;

import io.lpgph.ddd.event.model.EventStoredRepository;
import io.lpgph.ddd.utils.json.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Component
public class UserTask {

  private final EventStoredRepository eventStoredRepository;

//  @Scheduled(cron = "*/1 * * * * ?")
//  public void po() {
//    System.out.println(System.currentTimeMillis());
//  }
//
//  @Scheduled(cron = "*/2 * * * * ?")
//  public void event() {
//    Random random = new Random(1000);
//
//    eventStoredRepository.saveAll(
//        eventStoredRepository.findAllByStatusIn(Set.of(0, 2)).stream()
//            .peek(
//                item -> {
//                  try {
//                    log.info("\n{}\n", JsonUtil.toJson(item.getEvent()));
//                    if (random.nextInt(10) > 2) throw new RuntimeException("模拟消息队列失效");
//                    item.changeSuccess();
//                  } catch (Exception e) {
//                    log.error(e.getMessage());
//                    item.changeFailed();
//                  }
//                })
//            .collect(Collectors.toSet()));
//  }
}
