package io.lpgph.res.doc.common.bean.result;

public enum ResultCode implements Code {

    /**
     * 用户未登录
     */
    NOT_LOGIN("-1"),

    /**
     * 操作异常
     */
    ERROR("0"),

    /**
     * 操作成功
     */
    SUCCESS("1");

    private String code;

    ResultCode(String value) {
        this.code = value;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return null;
    }
}
