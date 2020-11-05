package com.zdb.demo.interceptor;

import com.zdb.demo.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 后台登陆拦截器
 */
@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 打印請求者的ip
        System.out.println("ip:" + request.getRemoteAddr());
        // 不需要拦截的请求地址(拼接成一个字符串)
        StringBuilder noInterceptPath = new StringBuilder();
        // 获取目标访问地址
        String desPath = request.getServletPath();
        // -1.判断是否是拦截器要拦截的服务接口，获取session和页面上的access_token信息
        if(!noInterceptPath.toString().contains(desPath)) {
            log.info("====================进入拦截器========================");
            HttpSession session = request.getSession();
          /*  if(userGetSession != null){
                return true;
            }*/
//          方便调试
            User user = new User();
            user.setUserId(1);
            user.setUserName("zdb");
            user.setUserPassWord("123");
            session.setAttribute("user",user);

        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) {

    }
}
