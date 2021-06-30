package io.lpgph.ddd.common.exception;


public enum CommonErrorCode implements ErrorCode {
    LOCK_OCCUPIED(409, "任务已被锁定，请稍后重试"),
    PARAM_NOT_NULL(401, "参数不能为空！"),
    PARAM_NOT_EMPTY(401, "参数不能为空！"),
    SYSTEM_ERROR(500, "系统错误");
    private final int status;
    private final String message;

    CommonErrorCode(int status, String message) {
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
