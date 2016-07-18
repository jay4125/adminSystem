package com.linkon.common.exception;

/**
 * 自定义业务异常
 * 
 * @ClassName: BusinessExcption
 * @Description: 自定义业务异常
 * @author zhouxiao
 * @date 2016年4月3日 下午8:22:20
 *
 */
public class BusinessExcption extends Exception {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;

	/** 错误编号 */
	private int code;
	/** 错误消息 */
	private String message;

	public BusinessExcption() {
		super();
	}

	public BusinessExcption(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public BusinessExcption(String msg, Throwable cause) {
		super(msg, cause);
	}

	public BusinessExcption(String message) {
		super(message);
		this.message = message;
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
