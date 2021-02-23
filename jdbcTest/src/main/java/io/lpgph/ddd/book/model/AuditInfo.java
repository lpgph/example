package io.lpgph.ddd.book.model;

import lombok.*;
import org.springframework.data.annotation.*;

import java.time.LocalDateTime;

@Getter
@Setter
public class AuditInfo {

  /** 创建时间 */
  @CreatedDate private LocalDateTime gmtCreate;

  /** 创建人 */
  @CreatedBy private Long createdBy;

  /** 最后修改时间 */
  @LastModifiedDate private LocalDateTime gmtModified;

  /** 修改入 */
  @LastModifiedBy private Long modifiedBy;
}
