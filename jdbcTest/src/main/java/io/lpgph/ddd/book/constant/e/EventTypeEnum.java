package io.lpgph.ddd.book.constant.e;

import io.lpgph.ddd.common.BaseEnum;

import java.util.HashMap;
import java.util.Map;

public enum EventTypeEnum implements BaseEnum {
  // 启用
  SAVE(0),
  // 禁用
  UPDATE(1),
  DELETE(2);

  EventTypeEnum(Integer value) {
    this.value = value;
  }

  private final Integer value;

  public int getValue() {
    return value;
  }

  private static final Map<Integer, EventTypeEnum> mappings = new HashMap<>(16);

  static {
    for (EventTypeEnum e : values()) {
      mappings.put(e.getValue(), e);
    }
  }

  public static EventTypeEnum resolve(Integer value) {
    return (value != null ? mappings.get(value) : null);
  }

  public boolean matches(Integer value) {
    return (this == resolve(value));
  }
}
