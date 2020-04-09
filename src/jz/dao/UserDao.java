package jz.dao;

import jz.pojo.User;

/**
 * @param
 * @description
 * @Created by IntelliJ IDEA.
 * @author:
 * @Date: 2020/3/23
 * @return
 */
public interface UserDao {
    /*
    *根据用户名查询用户信息
     * @Author jz
     * @Description //TODO
     * @Date
     * @Param
     * @return 如果返回unll说明用户不存在
     **/
     User queryUserByUsername(String username);
     /*
     *
      * @Author jz
      * @Description //TODO
      * @Date
      * @Param
      * @return 如果返回unll说明用户名或密码错误
      **/
     User queryUserByUsernameAndPassword(String username,String password);

    /*
    *保存用户信息
     * @Author jz
     * @Description //TODO
     * @Date
     * @Param
     * @return
     **/
    int saveUser(User user);
}
