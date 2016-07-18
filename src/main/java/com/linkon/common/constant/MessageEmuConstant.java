package com.linkon.common.constant;

public enum MessageEmuConstant {
	
	BUSINESS_LOGIN_PARAM_ERROR(10000,"登陆参数传入错误"),
	BUSINESS_LOGIN_LOGIN_NAME_ERROR(10001,"登录名不存在")
	;

    //消息
    private String message;
    //编码
    private int code;

    //枚举类不能实例化
    private MessageEmuConstant(int code, String message) {
        this.code = code;
        this.message = message;
    }


    /**
     * 根据码获取信息
     * @param code
     * @return
     */
    public static String getMessage(int code) {
        for (MessageEmuConstant c : MessageEmuConstant .values()) {
            if (c.getCode() == code) {
                return c.getMessage();
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
	

}
