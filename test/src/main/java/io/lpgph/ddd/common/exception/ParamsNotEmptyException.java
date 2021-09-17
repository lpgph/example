package io.lpgph.ddd.common.exception;

import java.util.HashMap;
import java.util.Map;

public class ParamsNotEmptyException extends AppException {
  public ParamsNotEmptyException(Map<String, Object> data) {
    super(SpringCommonErrorCode.REQUEST_VALIDATION_FAILED, data);
  }

  public ParamsNotEmptyException() {
    super(SpringCommonErrorCode.REQUEST_VALIDATION_FAILED, new HashMap<>());
  }
}
