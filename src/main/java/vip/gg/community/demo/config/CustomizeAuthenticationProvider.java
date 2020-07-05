package vip.gg.community.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
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
 * Date on 2020/6/13  10:52 上午
 */
@Component
public class CustomizeAuthenticationProvider implements AuthenticationProvider {


    @Autowired(required = false)
    UserAuthMapper userMapper;

    @Autowired
    HttpServletRequest request;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //数据库查询用户
        String username = authentication.getPrincipal().toString();
        UserAuthExample userExample = new UserAuthExample();
        userExample.createCriteria().andNameEqualTo(username);
        List<UserAuth> users = userMapper.selectByExample(userExample);

        if (users == null) {
            throw new BadCredentialsException("查无此用户");
        }
        UserAuth user = users.get(0);
        if (user.getName() != null && user.getAccountId().equals(authentication.getCredentials())) {
                request.getSession().setAttribute("user",user);
            Collection<? extends GrantedAuthority> authorities = AuthorityUtils.NO_AUTHORITIES;

            return new UsernamePasswordAuthenticationToken(user.getName(), user.getAccountId(), authorities);
        } else {
            throw new BadCredentialsException("用户名或密码错误。");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
