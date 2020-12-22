package io.lpgph.oidc.identity.user;

import java.util.Set;

/** 实现 用户与权限 管理 */
public class User {

  // 用户拥有的角色
  private Set<Long> roles;

  // 用户所属组 根据需要是否分组和角色是模糊可替换的
  //  private Set<Long> groups;
}
