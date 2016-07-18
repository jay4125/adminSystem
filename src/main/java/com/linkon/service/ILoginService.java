package com.linkon.service;

import com.linkon.common.exception.BusinessExcption;
import com.linkon.entity.bean.AdminUser;

/**
 * 登陆接口
* @ClassName: ILoginService
* @Description: 登陆接口
* @author zhouxiao
* @date 2016年4月3日 下午8:19:06
*
 */
public interface ILoginService{
	
	/**
	 * 
	* @Title: login
	* @Description: 登陆
	* @param loginName
	* @param password
	* @param @throws BusinessExcption    设定文件
	* @return AdminUser    返回类型
	* @throws
	* @author zhouxiao
	 */
	AdminUser login(String loginName,String password) throws BusinessExcption;
}
