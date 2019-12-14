package com.atguigu.gmall.interceptors;

import com.atguigu.gmall.annotations.LoginRequired;
import com.atguigu.gmall.util.CookieUtil;
import com.atguigu.gmall.util.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Description:
 * ClassName: LoginInterceptor
 * date: 2019/12/12 22:25
 *
 * @author fancy Email:395765197@qq.com
 * @version 1.0
 * @since JDK 1.8
 */
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod method = (HandlerMethod) handler;

        LoginRequired loginRequired = method.getMethodAnnotation(LoginRequired.class);

        if (loginRequired == null) {
            return true;
        }

        //获取cookie中的oldToken
        String oldToken = CookieUtil.getCookieValue(request, "oldToken", true);

        //获取url中的newToken
        String newToken = request.getParameter("newToken");

        String token = "";

        if (StringUtils.isNoneBlank(oldToken)) {
            token = oldToken;
        }

        if (StringUtils.isNoneBlank(newToken)) {
            token = newToken;
        }

        //获取当前请求ip
        String remoteAddr = request.getRemoteAddr();

        //判断是否有token
        if (StringUtils.isNotBlank(token)) {
            //使用jwt工具验证token
            Map<String, Object> userInfo = JwtUtil.decode(token, "gmall", remoteAddr);

            if (userInfo != null && userInfo.size() > 0) {
                //验证成功,获取用户信息
                request.setAttribute("memberId", userInfo.get("memberId"));
                request.setAttribute("nickname", userInfo.get("nickname"));

                //刷新cookie过期时间
                CookieUtil.setCookie(request, response, "oldToken", token, 60 * 60, true);

                return true;
            }
        }

        if (!loginRequired.isMustSuccess()) {
            return true;
        }

        //获取当前请求url
        StringBuffer ReturnUrl = request.getRequestURL();

        //重定向验证中心登录
        response.sendRedirect("http://passport.gmall.com:8087/index?ReturnUrl=" + ReturnUrl + "&remoteAddr=" + remoteAddr);

        return false;
    }
}
