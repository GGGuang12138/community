package vip.gg.community.demo.config;

import org.apache.commons.codec.digest.Md5Crypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.Md4PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import sun.security.provider.MD5;
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
public class FormAuthenticationProvider implements AuthenticationProvider {


    @Autowired(required = false)
    UserAuthMapper userAuthMapper;

    @Autowired
    HttpServletRequest request;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //数据库查询用户
        String username = authentication.getPrincipal().toString();
        UserAuthExample userExample = new UserAuthExample();
        userExample.createCriteria().andNameEqualTo(username);
        List<UserAuth> users = userAuthMapper.selectByExample(userExample);

        if (users == null || users.size() == 0) {
            throw new BadCredentialsException("查无此用户");
        }
        UserAuth user = users.get(0);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        //String encodePassword = bCryptPasswordEncoder.encode((String)authentication.getCredentials());

        if (user.getName() != null && bCryptPasswordEncoder.matches((String)authentication.getCredentials(),user.getPassword())) {
                request.getSession().setAttribute("user",user);
            Collection<? extends GrantedAuthority> authorities = AuthorityUtils.NO_AUTHORITIES;

            return new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword(), authorities);
        } else {
            throw new BadCredentialsException("用户名或密码错误。");
        }
    }

    /**
     * providerManager会遍历provider
     * 根据该方法来决定用哪个authentication进行验证
     * @param authentication
     * @return
     */
    @Override
    public boolean supports(Class<?> authentication) {
        //Boolean b = UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
        return  UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
