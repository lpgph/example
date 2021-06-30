package io.lpgph.ddd.user.representation.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

@Schema(description = "__jdbc_user__选项")
@Value
public class UserOptionResult {

  @Schema(description = "自增id")
  Long id;

  @Schema(description = "名称")
  String name;
}
