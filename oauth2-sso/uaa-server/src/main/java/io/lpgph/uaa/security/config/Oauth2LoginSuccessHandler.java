package io.lpgph.uaa.security.config;

import io.lpgph.uaa.common.json.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class Oauth2LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

  @Override
  public void onAuthenticationSuccess(
      HttpServletRequest request, HttpServletResponse response, Authentication authentication)
      throws ServletException, IOException {
    log.info("\n\noauth2 \n {}\n\n", JsonUtil.toJson(authentication));

    // 查找用户数据库中是否存在该用户  如果存在该用户则组装成 UsernamePasswordAuthenticationToken 返回
    // 如果不存在则跳转到用户注册页面 绑定邀请人  绑定后跳转到 /oauth/authorize 获取token
    // 如果在这里注册 则需要需要判断邀请人是否存在 然后创建用户 发送绑定邀请人事件
    // 如果在推广服务注册 则需要共享session
    super.onAuthenticationSuccess(request, response, authentication);
  }
}
