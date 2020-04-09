package jz.dao.impl;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @param
 * @description
 * @Created by IntelliJ IDEA.
 * @author:
 * @Date: 2020/4/7
 * @return
 */
public class OrderDaoImplTest {

    @Test
    public void queryMyOrders() {

        OrderDaoImpl orderDao = new OrderDaoImpl();
        try {
            System.out.println(orderDao.queryMyOrders(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}