package org.xwsx.constant;

public enum PageCodeEnum {
    //返回码，定义成4位，前两位表示动作，例如新增，删除修改，后面为表示状态，成功，失败之类的
    //前两位----------> 10表示新增，11表示删除,12修改,13登陆,14
    //后两位---------->00表示成功,01表示失败,02表示超时

    ADD_SUCCESS(1000,"新增成功!"),
    DELETE_SUCCESS(1100,"删除成功"),
    UPDATE_SUCCESS(1200,"修改成功"),
    UPDATEP_FAIL(1201,"修改失败"),
    DELETE_FAIL(1101,"删除失败"),
    ADD_FAIL(1001,"新增失败!"),
    LOGIN_FAIL(1301,"登陆失败"),
    USERNAME_EXITES(1411,"用户名已存在"),
    LOGIN_TIMEOUT(1302,"登陆超时,请重新登陆");

    public static String getKEY() {
        return KEY;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static final String KEY = "pageCode";
    private Integer code;
    private String msg;

    PageCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
