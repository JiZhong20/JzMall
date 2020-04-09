package jz.test;

import jz.pojo.Cart;
import jz.pojo.CartItem;
import jz.service.OrderService;
import jz.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @param
 * @description
 * @Created by IntelliJ IDEA.
 * @author:
 * @Date: 2020/4/3
 * @return
 */
public class OrderServiceTest {

    @Test
    public void createOrder() {

        Cart cart = new Cart();

        cart.addItem(new CartItem(1, "java从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1, "java从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2, "数据结构与算法", 1, new BigDecimal(100),new BigDecimal(100)));

        OrderService orderService = new OrderServiceImpl();

        System.out.println( "订单号是：" + orderService.createOrder(cart, 1) );

    }
}