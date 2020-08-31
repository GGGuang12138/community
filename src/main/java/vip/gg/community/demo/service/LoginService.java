package vip.gg.community.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vip.gg.community.demo.mapper.UserAuthMapper;
import vip.gg.community.demo.model.UserAuth;
import vip.gg.community.demo.model.UserAuthExample;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Creat by GG
 * Date on 2020/6/9  11:36 上午
 */
@Service
public class LoginService implements UserDetailsService {

    @Autowired(required = false)
    UserAuthMapper userMapper;



    @Autowired
    HttpServletRequest request;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserAuthExample userExample = new UserAuthExample();
        userExample.createCriteria().andNameEqualTo(s);
        List<UserAuth> users = userMapper.selectByExample(userExample);
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("user"));
        if(users.size() != 0){
            UserAuth user = users.get(0);
            //request.getSession().setAttribute("user",user);//没有验证就返回用户名字
            return new org.springframework.security.core.userdetails.User(user.getName(),user.getAccountId(),authorities);
        }
        else {
            throw new UsernameNotFoundException("用户名不存在！");
        }
    }
}
