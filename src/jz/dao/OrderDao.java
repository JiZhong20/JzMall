package jz.dao;

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
public interface OrderDao {
    public int saveOrder(Order order);
    /**
     * 查询我的订单
     *
     * @param userId
     *            用户id
     *
     * @return 用户的订单
     * @throws Exception
     */
    public List<Order> queryMyOrders(int userId) throws Exception;

    /**
     * 查询所有订单
     *
     * @return 返回所有订单信息
     * @throws Exception
     */
    public List<Order> queryAllOrders() throws Exception;

    /**
     * 修改订单状态
     *
     * @param status
     *            订单的状态
     * @throws Exception
     */
    public void updateOrderStatus(int status,String orderId) throws Exception;



}
