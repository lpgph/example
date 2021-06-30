package io.lpgph.ddd.user.exception;

import io.lpgph.ddd.common.exception.ErrorCode;


public enum UserErrorCode implements ErrorCode {
  NOT_FOUND(404, "没有找到！"),
  NAME_EXISTED(400, "名称已存在！"),
  NOT_DISCARD(400, "不是废弃状态"),
  IS_DISCARD(400, "是废弃状态"),
  ;

  private final int status;
  private final String message;

  UserErrorCode(int status, String message) {
    this.status = status;
    this.message = message;
  }

  @Override
  public int getStatus() {
    return status;
  }

  @Override
  public String getMessage() {
    return message;
  }

  @Override
  public String getCode() {
    return this.name();
  }
}

