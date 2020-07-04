package vip.gg.community.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vip.gg.community.demo.mapper.UserAuthMapper;
import vip.gg.community.demo.model.UserAuth;
import vip.gg.community.demo.model.UserAuthExample;
import vip.gg.community.demo.model.UserInfo;

import java.util.List;

/**
 * Creat by GG
 * Date on 2020/2/20  11:32 上午
 */
@Service
public class Userservice {

    @Autowired(required = false)
    private UserAuthMapper userMapper;

    public void update(UserAuth user) {
        UserAuthExample userExample = new UserAuthExample();
        userExample.createCriteria().andAccountIdEqualTo(user.getAccountId());
        List<UserAuth> dbusers = userMapper.selectByExample(userExample);
        if(dbusers.size() == 0){
            //插入
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        }else {
            //更新user
            UserAuth dbuser = dbusers.get(0);

            UserAuth updateuser =new UserAuth();
            updateuser.setToken(user.getToken());
            updateuser.setName(user.getName());
            updateuser.setAccountId(user.getAccountId());
            updateuser.setAvatarUrl(user.getAvatarUrl());

            UserAuthExample example = new UserAuthExample();
            example.createCriteria().andIdEqualTo(dbuser.getId());
            userMapper.updateByExampleSelective(updateuser, example);
        }
    }
}
