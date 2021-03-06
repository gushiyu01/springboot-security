package com.gsy.security.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class MyLogoutFilter implements LogoutHandler {

    @Override
    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {
        try{
            String redUrl = "selfLogout";
            httpServletResponse.sendRedirect(redUrl);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
