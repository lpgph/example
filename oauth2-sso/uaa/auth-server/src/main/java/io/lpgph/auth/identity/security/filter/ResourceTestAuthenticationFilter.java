package io.lpgph.auth.identity.security.filter;

import io.lpgph.auth.common.json.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class ResourceTestAuthenticationFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    log.info(
        "\n\n当前请求 {}\naccess_token\n  {}\nAuthorization\n  {}\ngetCookies\n  {}\n\n",
        request.getRequestURI(),
        JsonUtil.toJson(request),
        JsonUtil.toJson(request.getHeader("Authorization")),
        JsonUtil.toJson(request.getCookies()));
    filterChain.doFilter(request, response);
  }
}
