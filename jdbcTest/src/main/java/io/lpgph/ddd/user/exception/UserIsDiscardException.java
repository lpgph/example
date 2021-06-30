package io.lpgph.ddd.user.exception;

import io.lpgph.ddd.common.exception.AppException;
import java.util.Map;

public class UserIsDiscardException extends AppException {

  public UserIsDiscardException(String name) {
    super(
        UserErrorCode.NAME_EXISTED, Map.of("name", name));
  }
  public UserIsDiscardException(Long id) {
    super(
        UserErrorCode.NAME_EXISTED, Map.of("id", id));
  }
  public UserIsDiscardException() {
    super(
        UserErrorCode.NAME_EXISTED, Map.of());
  }
}