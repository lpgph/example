package io.lpgph.ddd.policy;

import lombok.Value;
import org.springframework.data.annotation.*;

import java.time.LocalDateTime;

@Value
public class Auditor {

  /** 创建时间 */
  @CreatedDate LocalDateTime createdDate;

  /** 创建人 */
  @CreatedBy Long createdBy;

  /** 最后修改时间 */
  @LastModifiedDate LocalDateTime modifiedDate;

  /** 修改入 */
  @LastModifiedBy Long modifiedBy;

  /** 乐观锁 */
  @Version Long version;
}
