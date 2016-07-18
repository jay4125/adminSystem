package com.linkon.service.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.linkon.common.constant.CommonConstant;
import com.linkon.common.util.ConfUtil;

/**
 * 登陆拦截涉及到不拦截的页面
* @ClassName: LoginInterceptor
* @Description: 登陆拦截
* @author zhouxiao
*/
public class LoginInterceptor implements HandlerInterceptor{

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
        
		 // 请求的uri
        String uri = request.getRequestURI();
        String ctxpath = request.getContextPath();
        if (!ctxpath.endsWith("/")) {
            ctxpath += "/";
        }
        if(ctxpath.equalsIgnoreCase(uri)){
            return true;
        }
        
        //公开地址 全部放过       
        
        //不过滤的配置url
        for (String s : CommonConstant.notFilter) {
            if (uri.indexOf(s) != -1) {
                return true;
            }
        }
        
        //判断session
        HttpSession session = request.getSession();
        
        //从session中拿，看看有没有
        Object currUser = session.getAttribute(CommonConstant.session_loginUser);

        if (currUser != null) {
            return true;
        }
       
        
        response.sendRedirect(ConfUtil.getConf("LOCAL_URL")+"index/index");
        
		return false;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
