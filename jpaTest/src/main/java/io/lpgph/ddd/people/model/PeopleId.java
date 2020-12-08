package io.lpgph.ddd.people.model;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Embeddable
public class PeopleId implements Serializable {

  private Long id;

  public static PeopleId create(Long id) {
    return new PeopleId(id);
  }
}
