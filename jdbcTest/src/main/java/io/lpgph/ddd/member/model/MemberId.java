package io.lpgph.ddd.member.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Value
public class MemberId {

  Long id;

  public static MemberId create(Long id) {
    return new MemberId(id);
  }
}
