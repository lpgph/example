package io.lpgph.ddd.common.exception;



import java.util.HashMap;

public class SystemException extends AppException {

    public SystemException(Throwable cause) {
        super(CommonErrorCode.SYSTEM_ERROR, new HashMap<String, Object>(){
            {
                put("detail", cause.getMessage());
            }
        }, cause);
    }
}
