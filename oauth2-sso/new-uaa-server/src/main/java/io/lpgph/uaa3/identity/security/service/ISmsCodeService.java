package io.lpgph.uaa3.identity.security.service;

/** 短信服务 */
public interface ISmsCodeService {

  void sendCode(String phone);

  String getCode(String phone);

  void invalid(String phone,String code);
}