package io.lpgph.ddd.shopping.model;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Embeddable
public class ShopCardId implements Serializable {

  private Long userId;
  private Long goodsId;

  public static ShopCardId create(Long userId, Long goodsId) {
    return new ShopCardId(userId, goodsId);
  }
}
