package vip.gg.community.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;
import vip.gg.community.demo.mapper.UserAuthMapper;
import vip.gg.community.demo.model.UserAuth;
import vip.gg.community.demo.model.UserAuthExample;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;

/**
 * Creat by GG
 * Date on 2020/7/10  10:28 上午
 */
@Component
public class TokenAuthenticationProvider implements AuthenticationProvider {

    @Autowired(required = false)
    private UserAuthMapper userAuthMapper;

    @Autowired
    HttpServletRequest request;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        CustomizeAuthenticationToken customizeAuthenticationToken = (CustomizeAuthenticationToken) authentication;
        String token = customizeAuthenticationToken.getPrincipal().toString();
        UserAuthExample userExample =  new UserAuthExample();
        userExample.createCriteria().andTokenEqualTo(token);
        List<UserAuth> users = userAuthMapper.selectByExample(userExample);
        if (users == null) {
            throw new BadCredentialsException("查无此用户");
        }
        UserAuth user = users.get(0);
        if (user.getName() != null) {
            request.getSession().setAttribute("user",user);
            System.out.println("token");
            Collection<? extends GrantedAuthority> authorities = AuthorityUtils.NO_AUTHORITIES;

            return new CustomizeAuthenticationToken(user.getName(),authorities);
        } else {
            throw new BadCredentialsException("用户名或密码错误。");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
//        Boolean flag = authentication.equals(CustomizeAuthenticationToken.class);
        return CustomizeAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
