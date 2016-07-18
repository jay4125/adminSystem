package com.linkon.service.iml;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linkon.common.constant.CommonConstant;
import com.linkon.common.constant.MessageConstant;
import com.linkon.common.exception.BusinessExcption;
import com.linkon.dao.mapper.AdminUserMapper;
import com.linkon.entity.bean.AdminUser;
import com.linkon.service.ILoginService;

/**
 * 
* @ClassName: LoginService
* @Description: 登陆业务逻辑层
* @author zhouxiao
* @date 2016年4月3日 下午8:44:09
*
 */

@Service("loginService")
public class LoginService implements ILoginService {

	@Autowired
	private AdminUserMapper adminUserMapper;
	
	
	@Override
	public AdminUser login(String loginName, String password) throws BusinessExcption {
		
		//1.参数校验
		if (StringUtils.isBlank(loginName) || StringUtils.isBlank(password)){
			throw new BusinessExcption(CommonConstant.BUSINESS_ERROR,MessageConstant.BUSINESS_LOGIN_PARAM_ERROR);
		}
		
		//2.根据用户名去adminUser表查询
		AdminUser user = adminUserMapper.queryAdminUserByName(loginName);
		
		//3.判断用户名，密码是否错误
		if (user == null){
			throw new BusinessExcption(CommonConstant.BUSINESS_ERROR,MessageConstant.BUSINESS_LOGIN_LOGIN_NAME_ERROR);
		}
		
		String pwd = DigestUtils.md5Hex(password);
		if (!pwd.equals(user.getPassword())) {
			 throw new BusinessExcption(CommonConstant.BUSINESS_ERROR,MessageConstant.BUSINESS_LOGIN_LOGIN_PWD_ERROR);
		}	
		
		if (user.getStatus() == null || user.getStatus().equals(CommonConstant.DISABLE_USER_STATUS)){
			throw new BusinessExcption(CommonConstant.BUSINESS_ERROR,MessageConstant.BUSINESS_LOGIN_LOGIN_STATUS_ERROR);
		}
		
		return user;

	}

}
