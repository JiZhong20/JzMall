package jz.dao;

import jz.pojo.OrderItem;

import java.util.List;

/**
 * @param
 * @description
 * @Created by IntelliJ IDEA.
 * @author:
 * @Date: 2020/4/2
 * @return
 */
public interface OrderItemDao {

        public int saveOrderItem(OrderItem orderItem);

        List<OrderItem> queryOrderItems(String orderId);
}
