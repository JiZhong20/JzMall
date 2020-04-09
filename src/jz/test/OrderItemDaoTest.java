package jz.test;

import jz.dao.OrderItemDao;
import jz.dao.impl.OrderItemDaoImpl;
import jz.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @param
 * @description
 * @Created by IntelliJ IDEA.
 * @author:
 * @Date: 2020/4/3
 * @return
 */
public class OrderItemDaoTest {

    @Test
    public void saveOrderItem() {
        OrderItemDao orderItemDao = new OrderItemDaoImpl();

        System.out.println(orderItemDao.saveOrderItem(new OrderItem(null,"java程序设计",1,new BigDecimal(100),new BigDecimal(100),"1234567890")));
    }
}