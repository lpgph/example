package io.lpgph.ddd.common.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

/**
 * 复杂值对象基类<br>
 * 适用于 值对象需要再次进行关联 eg: 类目的值对象 分类属性 分类属性的值对象 分类属性值 <br>
 * 分类属性需要一个数据库主键 和 分类属性值进行关联 该主键没有意义 只是为了关联 不需要在聚合中表现 <br>
 * 不需要外部访问
 */
@Setter(value = AccessLevel.PRIVATE)
@Getter(value = AccessLevel.PROTECTED)
public abstract class AbstractValueObject {
  @Id private Long id;
}
