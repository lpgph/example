package io.lpgph.ddd.user.representation.params;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
public class UserQueryParams {
  Long id;
  String name;
  Boolean discard;
  Boolean enable;
  LocalDateTime afterDate;
  LocalDateTime beforeDate;
}
