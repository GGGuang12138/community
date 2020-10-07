package vip.gg.community.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vip.gg.community.demo.mapper.UserAuthMapper;
import vip.gg.community.demo.mapper.UserInfoMapper;
import vip.gg.community.demo.model.UserAuth;
import vip.gg.community.demo.model.UserAuthExample;
import vip.gg.community.demo.model.UserInfo;
import vip.gg.community.demo.model.UserInfoExample;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Creat by GG
 * Date on 2020/6/9  11:36 上午
 */
@Service
public class LoginService {

    @Autowired(required = false)
    UserAuthMapper userAuthMapper;

    @Autowired(required = false)
    UserInfoMapper userInfoMapper;

    public Long infoSyn(UserAuth user){
        UserInfo userInfo = new UserInfo();
        userInfo.setName(user.getName());
        userInfo.setAvatarUrl(user.getAvatarUrl());
        userInfo.setGmtCreate(user.getGmtCreate());
        userInfo.setGmtModified(user.getGmtModified());
        if (user.getAuthType() == "github" && user.getBio() != null){
            userInfo.setSign(user.getBio());
        }
        //信息同步到UserInfo表
        if (user.getUserId() != null){
            userInfo.setId(user.getUserId());
            userInfoMapper.updateByPrimaryKey(userInfo);
        }else{
            userInfoMapper.insert(userInfo);
            UserInfoExample userExample = new UserInfoExample();
            userExample.createCriteria().andNameEqualTo(user.getName());
            List<UserInfo> users = userInfoMapper.selectByExample(userExample);
            Long result = users.get(0).getId();
            return result;
        }
        return user.getUserId();

    }

}
