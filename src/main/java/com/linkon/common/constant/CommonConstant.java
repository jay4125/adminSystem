package com.linkon.common.constant;

/**
 * 公共常量类
* @ClassName: CommonConstant
* @Description: 公共常量类
* @author zhouxiao
* @date 2016年4月3日 下午8:40:35
*
 */
public class CommonConstant {
	
	/** 错误编码  **/
	public static int BUSINESS_ERROR = 0;
	
	/** 成功编码  **/
	public static int BUSINESS_SUCCESS = 1;
	
	/** 当前用户  **/
	public static final String session_loginUser = "currUser";
	
	/** 不过滤的配置  **/
    public static String[] notFilter = new String[] {
            "/index/login",
            "/index/logout",
            "/index/main",
            "/public",
            "/public/",
            "app/timeout"

    };
    
	/** 用户激活状态  **/
	public static Integer ACTIVE_USER_STATUS = 1;
	
	/** 用户禁止状态  **/
	public static Integer DISABLE_USER_STATUS = 0;

}
