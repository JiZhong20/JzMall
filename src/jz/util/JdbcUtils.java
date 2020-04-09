package jz.util;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @param
 * @description
 * @Created by IntelliJ IDEA.
 * @author:
 * @Date: 2020/3/22
 * @return
 */
public class JdbcUtils {
    private static DruidDataSource dataSource;
    private static ThreadLocal<Connection> connectionThreadLocal=new ThreadLocal<>();
    static {

        try {
            Properties properties = new Properties();
            //读取jdbc.properties属性配置文件
            InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            //从读取流中加载数据
            properties.load(inputStream);
            //创建数据库连接池
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
            System.out.println(dataSource.getConnection());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

    }

    /*
    *获取数据库连接池连接
     * @Author jz
     * @Description //TODO 
     * @Date
     * @Param 
     * @return 
     **/
    public static Connection getConnection(){
        Connection connection = connectionThreadLocal.get();
        if (connection == null){
            try {
                connection = dataSource.getConnection();
                connectionThreadLocal.set(connection);
                connection.setAutoCommit(false);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
    /*
    *关闭连接，放回数据库连接池
     * @Author jz
     * @Description //TODO
     * @Date
     * @Param
     * @return
     **
    public static void close(Connection connection){
    if (connection!=null){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    }*/

    public static void commitAndClose(){
        Connection connection = connectionThreadLocal.get();
        if (connection!=null){
            try {
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
            connectionThreadLocal.remove();
    }
    public static void rollbackAndClose(){
        Connection connection = connectionThreadLocal.get();
        if (connection!=null){
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        connectionThreadLocal.remove();
    }

}
