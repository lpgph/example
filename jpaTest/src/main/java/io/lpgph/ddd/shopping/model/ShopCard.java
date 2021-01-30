package io.lpgph.ddd.shopping.model;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
public class ShopCard {

  @EmbeddedId private ShopCardId id;

  private Integer num;

  public static ShopCard create(ShopCardId id, Integer num) {
    return ShopCard.builder().id(id).num(num).build();
  }

  public void changeNum(Integer num){
    this.num = num;
  }
}
