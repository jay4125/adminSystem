package com.linkon.controller;

import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 用户管理的action
* @ClassName: UserController
* @Description: 用户管理的action
* @author zhouxiao
* @date 2016年4月10日 下午2:35:41
*
 */
@Controller
@RequestMapping("user")
public class UserController extends BaseController {
	
	private static Logger loger = Logger.getLogger(UserController.class);
	
	@RequestMapping("/userList")  
	public ModelAndView queryUserList() throws Exception{
		
		return null;
		
	}
	

}
