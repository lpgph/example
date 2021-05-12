package io.lpgph.res.doc.common.bean.result;

public class Result<T> {

  private final Boolean success;

  private final T data;
  /** 错误码 */
  private final String code;
  /* 错误信息 */
  private final String msg;

  public Boolean getSuccess() {
    return success;
  }

  public T getData() {
    return data;
  }

  public String getCode() {
    return code;
  }

  public String getMsg() {
    return msg;
  }

  private Result(Boolean success, T data, String code, String msg) {
    this.success = success;
    this.data = data;
    this.code = code;
    this.msg = msg;
  }

  public static <T> ResultBuilder<T> builder() {
    return new ResultBuilder<T>();
  }

  public static <T> ResultBuilder<T> withSuccess(T data) {
    return Result.<T>builder().success(true).data(data);
  }

  public static <T> ResultBuilder<T> withError(String code, String msg) {
    return Result.<T>builder().success(false).code(code).msg(msg);
  }

  public static class ResultBuilder<T> {

    private Boolean success;
    private T data;
    private String code;
    private String msg;

    private ResultBuilder() {}

    public ResultBuilder<T> success(boolean success) {
      this.success = success;
      return this;
    }

    public ResultBuilder<T> data(T data) {
      this.data = data;
      return this;
    }

    public ResultBuilder<T> code(String code) {
      this.code = code;
      return this;
    }

    public ResultBuilder<T> msg(String msg) {
      this.msg = msg;
      return this;
    }

    @Override
    public String toString() {
      return "ResultBuilder(success = "
          + success
          + ", data = "
          + data
          + ", code = "
          + code
          + ", msg = "
          + msg
          + ")";
    }

    public Result<T> build() {
      return new Result<T>(success, data, code, msg);
    }
  }
}
