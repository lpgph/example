package io.lpgph.res.doc.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

@NoArgsConstructor(force = true)
@AllArgsConstructor
@Schema(description = "查询返回")
@Value
public class QueryResult {
  @Schema(description = "ID")
  Long id;

  @Schema(description = "名称")
  String name;
}
