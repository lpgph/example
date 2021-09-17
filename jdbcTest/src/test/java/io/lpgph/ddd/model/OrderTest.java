package io.lpgph.ddd.model;

import io.lpgph.ddd.utils.json.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Set;

@Slf4j
public class OrderTest {

  @Test
  public void t1() {
    Set<Item> items = Set.of(new Item(1L, 1L), new Item(2L, 2L), new Item(3L, 5L));
    Order order = new Order(1L, items);
    order
        .allItems()
        .forEach(
            item -> {
              if (item.getGoodsId().equals(3L)) {
                item.change(1L);
              }
            });
    order.getItems().forEach(item -> log.info(JsonUtil.toJson(item)));
  }
}
