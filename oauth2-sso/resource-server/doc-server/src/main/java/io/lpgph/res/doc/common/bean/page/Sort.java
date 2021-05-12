package io.lpgph.res.doc.common.bean.page;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Sort {

  private final String field;
  private final boolean asc;

  private Sort(String field, boolean asc) {
    this.field = field;
    this.asc = asc;
  }

  private Sort(String field) {
    this.field = field;
    this.asc = true;
  }

  public static Sort create(String sort) {
    if (sort == null || sort.trim().isEmpty()) return null;
    String regexZ = "^-?[A-Za-z]+";
    if (sort.matches(regexZ)) {
      return sort.startsWith("-") ? new Sort(sort.substring(1), false) : new Sort(sort);
    } else {
      return null;
    }
  }

  public static List<Sort> createList(String sort) {
    if (sort == null || sort.trim().isEmpty()) return null;
    String regexZ = "[A-Za-z\\-,]+";
    if (sort.matches(regexZ)) {
      return Arrays.stream(sort.split(","))
          .filter(item -> !item.trim().isEmpty())
          .map(Sort::create)
          .collect(Collectors.toList());
    } else {
      return null;
    }
  }

  public String getField() {
    return field;
  }

  public boolean isAsc() {
    return asc;
  }
}
