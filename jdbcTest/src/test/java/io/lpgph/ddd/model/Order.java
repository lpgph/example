package io.lpgph.ddd.model;

import lombok.Getter;

import java.util.Collections;
import java.util.Set;

@Getter
public class Order {
  private Long orderId;
  private Set<Item> items;
  private Long total;

  public Order(Long orderId, Set<Item> items) {
    this.orderId = orderId;
    this.change(items);
  }

  public Set<Item> allItems() {
    return Collections.unmodifiableSet(this.items);
  }

  public void change(Set<Item> items) {
    this.items = items;
    this.total = items.stream().mapToLong(Item::getQuantity).sum();
  }
}
