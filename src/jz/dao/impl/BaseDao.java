package jz.dao.impl;

import jz.util.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @param
 * @description
 * @Created by IntelliJ IDEA.
 * @author:
 * @Date: 2020/3/22
 * @return
 */
public abstract class BaseDao {
    //使用dbutils操作数据库

private QueryRunner queryRunner = new QueryRunner();
public int update(String sql,Object ... args){
    Connection connection = JdbcUtils.getConnection();
    try {
        return queryRunner.update(connection,sql,args);
    } catch (SQLException e) {
        e.printStackTrace();
        throw new RuntimeException(e);
    }


}
    public <T> T queryForOne(Class<T> type,String sql,Object ... args){
    Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.query(connection,sql,new BeanHandler<T>(type),args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
    /*
    * 查询多个
     * @Author jz
     * @Description //TODO
     * @Date
     * @Param
     * @return
     **/
    public <T>List<T> queryForList(Class<T> type,String sql,Object ... args){
        Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.query(connection,sql,new BeanListHandler<T>(type),args);
        }catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
    /*
    *查询单列数据
     * @Author jz
     * @Description //TODO
     * @Date
     * @Param
     * @return
     **/
    public Object queryForSingleValue(String sql,Object ... args){
        Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.query(connection,sql,new ScalarHandler(),args);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }



    }
}
