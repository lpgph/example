package io.lpgph.ddd.common.exception;



import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

class ErrorRepresentation {
  private final ErrorDetail error;

  ErrorRepresentation(AppException ex, String path) {
    ErrorCode error = ex.getError();
    this.error =
        new ErrorDetail(error.getCode(), error.getStatus(), error.getMessage(), path, ex.getData());
  }

  ErrorRepresentation(ErrorDetail error) {
    this.error = error;
  }

  ErrorDetail getError() {
    return error;
  }

  static class ErrorDetail {
    private final String code;
    private final int status;
    private final String message;
    private final String path;
    private final Instant timestamp;
    private final Map<String, Object> data = new HashMap<>();

    public ErrorDetail(
        String code, int status, String message, String path, Map<String, Object> data) {
      this.code = code;
      this.status = status;
      this.message = message;
      this.path = path;
      // Instant.now()使用等是UTC时间 与北京时间相差8小时
      this.timestamp = Instant.now().plusMillis(TimeUnit.HOURS.toMillis(8));
      if (data != null && !data.isEmpty()) {
        this.data.putAll(data);
      }
    }

    public int getStatus() {
      return status;
    }
  }
}
