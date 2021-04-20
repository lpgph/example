package io.lpgph.uaa3.identity.client;

import java.util.Set;

/**
 * 资源 <br>
 * 操作方式 <br>
 * rest api: GET POST PATCH DELETE PUT <br>
 * operations format : create update read delete allTasks restore <br>
 * 这里考虑资源和操作权限的处理方式 或者不需要操作权限 直接对角色和资源进行关联 <br>
 * 如 /user/info 有 read write update 三个权限 <br>
 * 复杂一点 分配资源后分配权限 <br>
 * 也可以 /user/info:read /user/info:write /user/info:update 三个权限 然后直接和角色关联
 */
public class Resource {

  // 资源名称
  private String name;

  // 资源标识符
  private String uri;

  /** 权限所属的客户端范围 */
  private Set<Long> scopes;

  /** 权限所属的角色 */
  private Set<Long> roles;
}
