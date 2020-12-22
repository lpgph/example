package io.lpgph.client;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.Assert;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.session.CookieWebSessionIdResolver;
import org.springframework.web.server.session.HeaderWebSessionIdResolver;
import org.springframework.web.server.session.WebSessionIdResolver;

import java.util.List;

public class SwitchWebSessionIdResolver implements WebSessionIdResolver {

  private HeaderWebSessionIdResolver headerWebSessionIdResolver;

  private CookieWebSessionIdResolver cookieWebSessionIdResolver;

  public static final String DEFAULT_SWITCH_NAME = "device_type";
  private String switchName = DEFAULT_SWITCH_NAME;

  public String getSwitchName() {
    return switchName;
  }

  public void setSwitchName(String switchName) {
    Assert.hasText(switchName, "'switchName' must not be empty");
    this.switchName = switchName;
  }

  public HeaderWebSessionIdResolver getHeaderWebSessionIdResolver() {
    if (headerWebSessionIdResolver == null) {
      headerWebSessionIdResolver = new HeaderWebSessionIdResolver();
    }
    return headerWebSessionIdResolver;
  }

  public void setHeaderWebSessionIdResolver(HeaderWebSessionIdResolver headerWebSessionIdResolver) {
    Assert.notNull(headerWebSessionIdResolver, "'headerWebSessionIdResolver' must not be null");
    this.headerWebSessionIdResolver = headerWebSessionIdResolver;
  }

  public CookieWebSessionIdResolver getCookieWebSessionIdResolver() {
    if (cookieWebSessionIdResolver == null) {
      cookieWebSessionIdResolver = new CookieWebSessionIdResolver();
    }
    return cookieWebSessionIdResolver;
  }

  public void setCookieWebSessionIdResolver(CookieWebSessionIdResolver cookieWebSessionIdResolver) {
    Assert.notNull(cookieWebSessionIdResolver, "'cookieWebSessionIdResolver' must not be null");
    this.cookieWebSessionIdResolver = cookieWebSessionIdResolver;
  }

  @Override
  public List<String> resolveSessionIds(ServerWebExchange exchange) {
    ServerHttpRequest request = exchange.getRequest();
    String device_type = request.getHeaders().getFirst(switchName);
    return device_type == null
        ? getCookieWebSessionIdResolver().resolveSessionIds(exchange)
        : getHeaderWebSessionIdResolver().resolveSessionIds(exchange);
  }

  @Override
  public void setSessionId(ServerWebExchange exchange, String id) {
    Assert.notNull(id, "'id' is required");
    ServerHttpRequest request = exchange.getRequest();
    String device_type = request.getHeaders().getFirst(switchName);
    if (device_type == null) {
      getCookieWebSessionIdResolver().setSessionId(exchange, id);
    } else {
      getHeaderWebSessionIdResolver().setSessionId(exchange, id);
    }
  }

  @Override
  public void expireSession(ServerWebExchange exchange) {
    ServerHttpRequest request = exchange.getRequest();
    String device_type = request.getHeaders().getFirst(switchName);
    if (device_type == null) {
      getCookieWebSessionIdResolver().expireSession(exchange);
    } else {
      getHeaderWebSessionIdResolver().expireSession(exchange);
    }
  }
}
