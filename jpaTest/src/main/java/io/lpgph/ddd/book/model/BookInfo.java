package io.lpgph.ddd.book.model;

import lombok.AllArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "book_info")
@Embeddable
public class BookInfo implements Serializable {

  @Column(name = "remark", nullable = false)
  private String remark;

  @Column(name = "tags", nullable = false)
  private String tags;

  public BookInfo() {
  }

  public BookInfo(String remark, String tags) {
    this.remark = remark;
    this.tags = tags;
  }
}
