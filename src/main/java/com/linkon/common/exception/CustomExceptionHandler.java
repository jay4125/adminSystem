package com.linkon.common.exception;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * spring统一的异常处理  --如果需要做程序错误调转错误页面可以采用此方式，暂时不考虑
* @ClassName: CustomExceptionHandler
* @Description: 统一的异常处理
* @author zhouxiao
* @date 2016年4月4日 下午1:03:56
*
 */
public class CustomExceptionHandler implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		// TODO Auto-generated method stub
		String message = null;
		BusinessExcption businessExcption = null;
		if (ex instanceof BusinessExcption){
			businessExcption = (BusinessExcption) ex;
		}else{
			businessExcption = new BusinessExcption("未知异常");
		}
		message = businessExcption.getMessage();
		
		request.setAttribute("msg", message);
		try {
			request.getRequestDispatcher("跳转页面路径").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ModelAndView();
	}

}
