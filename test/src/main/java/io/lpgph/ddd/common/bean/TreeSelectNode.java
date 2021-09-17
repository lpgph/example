package io.lpgph.ddd.common.bean;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class TreeSelectNode {
  Long value;
  String label;
  List<TreeSelectNode> children;
}
