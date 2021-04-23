package io.lpgph.ddd.policy;

import lombok.*;
import org.springframework.data.annotation.*;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.Set;

/** 客户端授权策略 如对角色进行授权 对用户进行授权 对客户端进行授权 对组进行授权 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Setter
@Getter
public  class ResourceServerPolicy {

  private Long clientId;

  private String name;

  private String description;

  /** 策略类型 基于角色 客户端 时间 用户 用户组 */
  private Integer type;

}
