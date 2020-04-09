package jz.dao.impl;

import jz.dao.OrderDao;
import jz.pojo.Order;

import java.util.List;

/**
 * @param
 * @description
 * @Created by IntelliJ IDEA.
 * @author:
 * @Date: 2020/4/2
 * @return
 */
public class OrderDaoImpl extends BaseDao implements OrderDao {
    @Override
    public int saveOrder(Order order) {
        String sql = "insert into t_order(`order_id`,`create_time`,`price`,`status`,`user_id`) values(?,?,?,?,?)";

        return update(sql,order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());
    }
    /**
     * 查询我的订单
     *
     * @param userId
     *            用户id
     *
     * @return 用户的订单
     * @throws Exception
     */
    public List<Order> queryMyOrders(int userId) throws Exception {
        // 查询我的订单
        String sql = "select `order_id` orderId,`create_time` createTime,`price`,`status`,`user_id` userId from t_order where `user_id` = ?";
        // 执行sql语句
        return queryForList(Order.class,sql,userId);
    }

    /**
     * 查询所有订单
     *
     * @return 返回所有订单信息
     * @throws Exception
     */
    public List<Order> queryAllOrders() throws Exception {
        // 查询我的订单
        String sql = "select `order_id` orderId,`create_time` createTime,`total_money` totalMoney,`status`,`user_id` userId from t_order ";
        // 执行sql语句
        return queryForList(Order.class,sql);
    }

    /**
     * 修改订单状态
     *
     * @param status
     *            订单的状态
     * @throws Exception
     */
    public void updateOrderStatus(int status, String orderId) throws Exception {
        // sql语句
        String sql = "update t_order set status = ? where order_id = ?";
        // 执行sql语句
        update(sql, status, orderId);



    }
}
