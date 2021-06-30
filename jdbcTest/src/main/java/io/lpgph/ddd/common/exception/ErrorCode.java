package io.lpgph.ddd.common.exception;

//This is to be subclassed by concrete enums
public interface ErrorCode {

//    int getHttpStatus();

    int getStatus();

    String getMessage();

    String getCode();
}
