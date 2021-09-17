package io.lpgph.ddd.user.command;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;
import java.time.LocalDateTime;
import java.util.Set;

@Value
@Schema(description = "__jdbc_user__")
public class CreateUserCommand {

    @Schema(description="")
    String name;
    @Schema(description="")
    Boolean vip;
    @Schema(description="")
    Integer level;
    @Schema(description="")
    Set<String> tags;
}
