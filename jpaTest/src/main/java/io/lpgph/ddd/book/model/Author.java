package io.lpgph.ddd.book.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDateTime;

@Embeddable
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Author implements Serializable {

  @Column(name = "people_id", nullable = false)
  private Long peopleId;

  @Column(name = "gmt_create", nullable = false)
  private LocalDateTime gmtCreate;

  public static Author create(Long peopleId) {
    return new Author(peopleId, LocalDateTime.now());
  }
}
