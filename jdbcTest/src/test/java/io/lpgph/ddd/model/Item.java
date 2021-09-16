package io.lpgph.ddd.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class Item {
  private Long goodsId;
  private Long quantity;

  public void change(long quantity) {
    this.quantity = quantity;
  }
}
