package vip.gg.community.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vip.gg.community.demo.mapper.UserMapper;
import vip.gg.community.demo.model.User;
import vip.gg.community.demo.model.UserExample;

import java.util.List;

/**
 * Creat by GG
 * Date on 2020/2/20  11:32 上午
 */
@Service
public class Userservice {

    @Autowired(required = false)
    private UserMapper userMapper;

    public void update(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andAccountIdEqualTo(user.getAccountId());
        List<User> dbusers = userMapper.selectByExample(userExample);
        if(dbusers.size() == 0){
            //插入
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        }else {
            //更新user
            User dbuser = dbusers.get(0);

            User updateuser =new User();
            updateuser.setToken(user.getToken());
            updateuser.setName(user.getName());
            updateuser.setAccountId(user.getAccountId());
            updateuser.setAvatarUrl(user.getAvatarUrl());

            UserExample example = new UserExample();
            example.createCriteria().andIdEqualTo(dbuser.getId());
            userMapper.updateByExampleSelective(updateuser, example);
        }
    }
}
