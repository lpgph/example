package io.lpgph.ddd.user.exception;

import io.lpgph.ddd.common.exception.AppException;
import java.util.Map;

public class UserNotFoundException extends AppException {

  public UserNotFoundException(String name) {
    super(
        UserErrorCode.NAME_EXISTED, Map.of("name", name));
  }
  public UserNotFoundException(Long id) {
    super(
        UserErrorCode.NAME_EXISTED, Map.of("id", id));
  }
  public UserNotFoundException() {
    super(
        UserErrorCode.NAME_EXISTED, Map.of());
  }
}