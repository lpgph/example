package io.lpgph.ddd.user.exception;

import io.lpgph.ddd.common.exception.AppException;
import java.util.Map;

public class UserNotDiscardException extends AppException {

  public UserNotDiscardException(String name) {
    super(
        UserErrorCode.NAME_EXISTED, Map.of("name", name));
  }
  public UserNotDiscardException(Long id) {
    super(
        UserErrorCode.NAME_EXISTED, Map.of("id", id));
  }
  public UserNotDiscardException() {
    super(
        UserErrorCode.NAME_EXISTED, Map.of());
  }
}