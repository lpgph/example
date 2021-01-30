package io.lpgph.ddd.order.model;

import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Builder
@Entity(name = "new_order")
@EntityListeners(AuditingEntityListener.class)
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ElementCollection
  @CollectionTable(
      name = "order_item",
      joinColumns = @JoinColumn(name = "order_id", nullable = false),
      foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
  private Set<Item> items;

  private String tag;
  //
  //  /** 创建时间 */
  //  @CreatedDate private LocalDateTime gmtCreate;
  //
  //  /** 创建人 */
  //  @CreatedBy private Long createdBy;
  //
  //  /** 最后修改时间 */
  //  @LastModifiedDate private LocalDateTime gmtModified;
  //
  //  /** 修改入 */
  //  @LastModifiedBy private Long modifiedBy;
  //
  //  @Version private Long version;

  public static Order create(final Set<Item> items) {
    return Order.builder().items(items).tag("order").build();
  }

  public void changeItems(final Set<Item> items) {
    this.items = items;
  }
}
