package io.lpgph.ddd.book.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;
import org.springframework.data.relational.core.mapping.Column;

import java.math.BigDecimal;

@Value
public class Monetary {

  //  /** 价格精确到分 */
  //  private final Long amount;
  //
  //  public MonetaryValue(BigDecimal anAmount) {
  //    this.amount = anAmount.multiply(BigDecimal.valueOf(1000)).longValue();
  //  }
  //
  //  public BigDecimal amount() {
  //    return BigDecimal.valueOf(amount).divide(BigDecimal.valueOf(1000), 3, RoundingMode.DOWN);
  //  }

  @Column("price")
  BigDecimal amount;

  public static Monetary create(BigDecimal anAmount) {
    return new Monetary(anAmount);
  }

  public Long amount() {
    return this.amount.multiply(BigDecimal.valueOf(1000)).longValue();
  }
}
