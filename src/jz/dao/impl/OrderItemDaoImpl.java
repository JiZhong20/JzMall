package jz.dao.impl;

import jz.dao.OrderItemDao;
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
public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item(`name`,`count`,`price`,`total_price`,`order_id`) values(?,?,?,?,?)";
        return update(sql,orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotalPrice(),orderItem.getOrderId());
    }

    /**
     * 查询订单项
     *
     * @param orderId
     *            查询的订单号
     * @throws Exception
     */
    public List<OrderItem> queryOrderItems(String orderId)   {
        // sql语句
        String sql = "select id,name,price,total_money totalMoney,count,order_id orderId from t_order_item where order_id = ?";
        // 执行sql语句
        List<OrderItem> result = queryForList(OrderItem.class,sql, orderId);
        // 返回
        return result;
    }


}
