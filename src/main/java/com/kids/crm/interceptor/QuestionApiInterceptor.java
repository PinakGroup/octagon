package com.kids.crm.interceptor;


import com.kids.crm.service.JwtToken;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class QuestionApiInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private JwtToken jwtToken;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!request.getRequestURI().contains("/etoken") && List.of("GET", "POST", "DELETE", "PUT").contains(request.getMethod().toUpperCase())) {
            String authorizationHeader = request.getHeader("Authorization");
            String token = authorizationHeader.substring(authorizationHeader.indexOf(" ") + 1);
            jwtToken.verifyToken(token).orElseThrow(RuntimeException::new);
        }


        return super.preHandle(request, response, handler);
    }

}
