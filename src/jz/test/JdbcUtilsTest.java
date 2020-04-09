package jz.test;

import jz.util.JdbcUtils;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

/**
 * @param
 * @description
 * @Created by IntelliJ IDEA.
 * @author:
 * @Date: 2020/3/22
 * @return
 */
public class JdbcUtilsTest {
    @Test
    public void testJdbcUtils(){
        System.out.println(JdbcUtils.getConnection());


        }
    }

