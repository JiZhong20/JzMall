package jz.service.impl;

import jz.dao.UserDao;
import jz.dao.impl.UserDaoImpl;
import jz.pojo.User;
import jz.service.UserService;

/**
 * @param
 * @description
 * @Created by IntelliJ IDEA.
 * @author:
 * @Date: 2020/3/23
 * @return
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    @Override
    public void registUser(User user) {
    userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public boolean existUsername(String username) {
        if (userDao.queryUserByUsername(username)==null){
            return false;//false说明没有查到相同用户名，则可用
        }
       return true;//用户名已存在，不可用
    }
}
