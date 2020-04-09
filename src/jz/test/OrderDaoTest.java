package jz.test;

import jz.dao.impl.OrderDaoImpl;
import jz.pojo.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @param
 * @description
 * @Created by IntelliJ IDEA.
 * @author:
 * @Date: 2020/4/2
 * @return
 */
public class OrderDaoTest {
    OrderDaoImpl orderDao = new OrderDaoImpl();
    @Test
    public void saveOrder() {


        orderDao.saveOrder(new Order("1234567890", new Date(), new BigDecimal(100), 0, 1));


    }



}


