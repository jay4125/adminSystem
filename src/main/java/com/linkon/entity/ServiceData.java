package com.linkon.entity;

import java.io.Serializable;

/**
 * 服务器响应前端的数据对象
 * @author zx
 *
 */
public class ServiceData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    public static final int STATUS_OK = 0;
    public static final int STATUS_ERROR = 1;
    
    private int status;// 状态 0 : success  ; 1: fail
    private String info;// msg 信息
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
	public ServiceData() {
	}
	public ServiceData(int status, String info) {
		this.status = status;
		this.info = info;
	}
    

}
