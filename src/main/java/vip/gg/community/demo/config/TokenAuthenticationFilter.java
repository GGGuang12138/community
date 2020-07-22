package vip.gg.community.demo.config;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Creat by GG
 * Date on 2020/6/12  1:53 下午
 */
public class TokenAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private AuthenticationManager authenticationManager;

    public TokenAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(new AntPathRequestMatcher("/githubLogin"));
        this.setAuthenticationManager(authenticationManager);
    }

//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
//        Cookie[] cookies = request.getCookies();
//        if(cookies!=null && cookies.length!=0) {
//            for (Cookie cookie : cookies) {
//                if (cookie.getName().equals("token")) {
//                    String token = cookie.getValue();
//                    CustomizeAuthenticationToken customizeAuthenticationToken = new CustomizeAuthenticationToken(token,new ArrayList<>());
//                    SecurityContextHolder.getContext().setAuthentication(customizeAuthenticationToken);
//                }
//            }
//        }
//        // 为空调用下一个过滤器
//        chain.doFilter(request,response);
//
//    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        Cookie[] cookies = request.getCookies();
        String token = null;
        if(cookies!=null && cookies.length!=0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    token = cookie.getValue();
                }
            }
        }
        CustomizeAuthenticationToken customizeAuthenticationToken = new CustomizeAuthenticationToken(token,new ArrayList<>());
        //setDetails(request, customizeAuthenticationToken);
        return this.getAuthenticationManager().authenticate(customizeAuthenticationToken);
    }

//    protected void setDetails(HttpServletRequest request,
//                              AbstractAuthenticationToken authRequest) {
//        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
//    }
}
