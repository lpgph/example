package io.lpgph.ddd.common.bean;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class TreeNode {
  Long key;
  String title;
  Boolean isLeaf;
  List<TreeNode> children;
}
