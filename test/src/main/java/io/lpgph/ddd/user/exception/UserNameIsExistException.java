package io.lpgph.ddd.user.exception;

import io.lpgph.ddd.common.exception.AppException;
import java.util.Map;

public class UserNameIsExistException extends AppException {

  public UserNameIsExistException(String name) {
    super(
        UserErrorCode.NAME_EXISTED, Map.of("name", name));
  }
  public UserNameIsExistException(Long id) {
    super(
        UserErrorCode.NAME_EXISTED, Map.of("id", id));
  }
  public UserNameIsExistException() {
    super(
        UserErrorCode.NAME_EXISTED, Map.of());
  }
}