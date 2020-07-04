package vip.gg.community.demo.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Creat by GG
 * Date on 2020/6/19  11:36 上午
 */
public class SuccessAuthenticationHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        String username = authentication.getAuthorities().toString();
        User user = new User();
        user.setName(username);
        request.getSession().setAttribute("user",user);
    }
}
