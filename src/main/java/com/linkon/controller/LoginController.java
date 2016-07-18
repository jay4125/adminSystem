package com.linkon.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.linkon.common.constant.CommonConstant;
import com.linkon.common.constant.MessageConstant;
import com.linkon.common.exception.BusinessExcption;
import com.linkon.entity.ServiceData;
import com.linkon.entity.bean.AdminUser;
import com.linkon.service.ILoginService;

/**
 * 登陆实现
 * @author zx
 *
 */
@Controller
@RequestMapping("index")
public class LoginController extends BaseController{
	
	private static Logger loger = Logger.getLogger(LoginController.class);  
	
	@Autowired
	private ILoginService loginService;
	
    @RequestMapping("index")
    public ModelAndView index(HttpServletRequest req, HttpServletResponse res, ModelMap data){
        return new ModelAndView("index",data);
    }
    
    @RequestMapping("main")
    public ModelAndView index(HttpServletRequest req, ModelMap data){
    	return new ModelAndView("main", data);
    }
    
	
	/**
	 * 登陆实现
	 * @param userName
	 * @param password
	 * @return
	 */
	@RequestMapping("login")
	@ResponseBody
	public ServiceData login(HttpServletRequest req){
		

		ServiceData serviceData = new ServiceData();
		
        HttpSession session = req.getSession();
        String loginName = req.getParameter("loginName");
        String password = req.getParameter("password");
		AdminUser user = null;
		try {
			user = loginService.login(loginName, password);
		} catch (BusinessExcption e) {
			loger.error(e.getMessage(), e);
			serviceData.setStatus(e.getCode());
			serviceData.setInfo(e.getMessage());
			return serviceData;
		}catch (Exception e) {
			loger.error(MessageConstant.UNKONW_ERROR, e);
			serviceData.setStatus(STATUS_ERROR);
			serviceData.setInfo(MessageConstant.UNKONW_ERROR);
			return serviceData;
		}
				
		session.setAttribute(CommonConstant.session_loginUser, user);
		
		//todo
		
		serviceData.setStatus(STATUS_OK);
		serviceData.setInfo(MessageConstant.BUSINESS_LOGIN_LOGIN_OK); 
		return serviceData;
		
	}

}
