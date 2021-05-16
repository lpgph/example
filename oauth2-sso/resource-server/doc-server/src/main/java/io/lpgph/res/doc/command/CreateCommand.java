package io.lpgph.res.doc.command;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Schema(description = "分类")
@Value
public class CreateCommand {

  @NotBlank
  @Size(max = 20)
  @Schema(description = "名称")
  String name;

  @Size(max = 255)
  @Schema(description = "描述")
  String description;
}
