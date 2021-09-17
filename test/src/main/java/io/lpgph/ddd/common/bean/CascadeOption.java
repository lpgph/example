package io.lpgph.ddd.common.bean;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Builder
@Value
public class CascadeOption {
  Long value;
  String label;
  Boolean isLeaf;
  List<CascadeOption> children;
}
