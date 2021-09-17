package io.lpgph.ddd;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(of = "name")
@Getter
@AllArgsConstructor
public class Cat {
  private Integer id;
  private String name;

  public void change(String name) {
    this.name = name;
  }
}
