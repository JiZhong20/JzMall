package jz.test;

import jz.pojo.User;
import jz.service.UserService;
import jz.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @param
 * @description
 * @Created by IntelliJ IDEA.
 * @author:
 * @Date: 2020/3/23
 * @return
 */
public class UserServiceTest {
    UserService userService = new UserServiceImpl();
    @Test
    public void registUser() {
        userService.registUser(new User(null,"def","666","66@qq.com"));
    }

    @Test
    public void login() {
        System.out.println(userService.login(new User(null,"def","666","66@qq.com")));
    }

    @Test
    public void existUsername() {
        if (userService.existUsername("abc")){
            System.out.println("用户名已存在！");
        }else {
            System.out.println("用户名可用");
        }
    }
}