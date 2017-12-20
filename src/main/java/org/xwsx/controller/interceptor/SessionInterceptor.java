package org.xwsx.controller.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.xwsx.constant.SessionKetConst;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Session拦截器(Spring拦截器)
 */
public class SessionInterceptor implements HandlerInterceptor{

    /**
     * 进入handle方法之前执行本方法
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request.getSession().getAttribute(SessionKetConst.KEY)!=null ){
            return true;
        }
        request.getRequestDispatcher("/login/sessionTimeout").forward(request,response);
        return false;
    }

    /**
     * 进入handle方法之后返回ModelAndView之前执行
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     * @return  true:执行下一个拦截器,直到所有拦截器都执行玩再执行被拦截的controller
     *          false:从当前这个拦截器往回执行之前的所有拦截器的afterCompletion()再退出整个拦截器链
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 在handle方法执行完之后执行
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
