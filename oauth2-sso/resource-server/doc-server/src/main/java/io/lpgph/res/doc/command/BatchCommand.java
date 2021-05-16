package io.lpgph.res.doc.command;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Schema(description = "批量操作")
@Value
public class BatchCommand {

  @Schema(description = "删除")
  Set<Long> delete;

  @Schema(description = "创建")
  Set<CreateCommand> create;

  @Schema(description = "更新")
  Set<CreateCommand> update;

  @Schema(description = "更新")
  Set<ChangeDiscardCommand> discardCommands;

  @Schema(description = "更新")
  Set<ChangeEnableCommand> enableCommands;
}
