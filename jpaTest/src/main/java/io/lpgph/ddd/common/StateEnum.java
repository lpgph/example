package io.lpgph.ddd.common;

import java.util.HashMap;
import java.util.Map;

public enum StateEnum implements IBaseEnum {
  // 禁用
  DEACTIVATED(0),
  // 启用
  ACTIVATED(1);

  StateEnum(Integer value) {
    this.value = value;
  }

  private final Integer value;

  @Override
  public int getValue() {
    return value;
  }

  private static final Map<Integer, StateEnum> mappings = new HashMap<>(16);

  static {
    for (StateEnum e : values()) {
      mappings.put(e.getValue(), e);
    }
  }

  public static StateEnum resolve(Integer value) {
    return (value != null ? mappings.get(value) : null);
  }

  public boolean matches(Integer value) {
    return (this == resolve(value));
  }
}
