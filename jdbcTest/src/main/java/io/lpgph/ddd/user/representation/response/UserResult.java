package io.lpgph.ddd.user.representation.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;
import java.time.LocalDateTime;

@Value
@Schema(description = "__jdbc_user__")
public class UserResult {
    @Schema(description="")
    Integer id;
    @Schema(description="")
    String name;
    @Schema(description="")
    Boolean vip;
    @Schema(description="")
    Integer level;
    @Schema(description="")
    Integer createdBy;
    @Schema(description="")
    LocalDateTime gmtCreate;
    @Schema(description="")
    LocalDateTime gmtModified;
    @Schema(description="")
    Integer modifiedBy;
    @Schema(description="")
    Integer version;
    @Schema(description="")
    Boolean delete;
}
