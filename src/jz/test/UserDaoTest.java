package jz.test;

import jz.dao.UserDao;
import jz.dao.impl.UserDaoImpl;
import jz.pojo.User;
import org.junit.jupiter.api.Test;

/**
 * @param
 * @description
 * @Created by IntelliJ IDEA.
 * @author:
 * @Date: 2020/3/23
 * @return
 */
public class UserDaoTest {
    UserDao userDao = new UserDaoImpl();
    @Test
    public void queryUserByUsername() {

        System.out.println(userDao.queryUserByUsername("jz"));
    }

    @Test
    public void queryUserByUsernameAndPassword() {
    }

    @Test
    public void saveUser() {
        System.out.println(userDao.saveUser(new User(null,"abc","123","123@qq.com")));
    }
}