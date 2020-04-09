package jz.service;

import jz.pojo.User;

/**
 * @param
 * @description
 * @Created by IntelliJ IDEA.
 * @author:
 * @Date: 2020/3/23
 * @return
 */
public interface UserService {
    //注册
    public void registUser(User user);
    //登录
    public User login(User user);
    //验证用户名是否可用
    public boolean existUsername(String username);

}
