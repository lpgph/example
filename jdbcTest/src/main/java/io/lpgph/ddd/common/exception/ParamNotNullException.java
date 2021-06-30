package io.lpgph.ddd.common.exception;

import java.util.Map;


public class ParamNotNullException extends AppException {

    public ParamNotNullException() {
        super(CommonErrorCode.SYSTEM_ERROR, Map.of());
    }
}
