package io.lpgph.ddd.common;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode(of = "id")
@ToString(of = "name")
@Getter
public abstract class Enumeration implements Comparable<Enumeration> {

  private int id;
  private String name;

  protected Enumeration(int id, String name) {
    this.id = id;
    this.name = name;
  }

  @Override
  public int compareTo(Enumeration object) {
    return object.getId();
  }
}
