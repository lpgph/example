package io.lpgph.uaa.identity.client;

import java.util.Set;

/** 实现客户端管理 客户端包含作用域 */
public class Client {

  // 客户端请求地址
  private String baseURL;

  // 自动授权
  private Boolean autoApprove;

  /** 客户端拥有的范围 */
  private Set<Long> scopes;
}
