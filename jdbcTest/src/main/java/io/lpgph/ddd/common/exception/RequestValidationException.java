package io.lpgph.ddd.common.exception;

import java.util.Map;

public class RequestValidationException extends AppException {
    public RequestValidationException(Map<String, Object> data) {
        super(SpringCommonErrorCode.REQUEST_VALIDATION_FAILED, data);
    }
}
