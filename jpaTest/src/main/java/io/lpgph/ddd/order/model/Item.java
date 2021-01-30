package io.lpgph.ddd.order.model;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

// 此处表示值对象 使用 @Value 注解更恰当 但是jpa 不支持
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(force = true, access = AccessLevel.PROTECTED)
@Embeddable
public class Item implements Serializable {

  Long goodsId;
  Integer quantity;

  public static Item create(Long goodsId, Integer quantity) {
    return new Item(goodsId, quantity);
  }
}
