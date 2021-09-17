package io.lpgph.ddd;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("test")
public class TestController {

  @GetMapping("/async")
  public Mono<Map<String, Object>> createOrder() {
    log.info("create Order");
    return Mono.create(
        monoSink -> {
          try {
            Map<String, Object> map = new HashMap<>();
            Thread.sleep(1000);
            map.put("test", "ttt");
            monoSink.success(map);
          } catch (Exception e) {
            e.printStackTrace();
            // 传播异常
            monoSink.error(e);
          }
        });
  }
}
