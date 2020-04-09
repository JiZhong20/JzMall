package jz.service;

import jz.pojo.Cart;
import jz.pojo.Order;
import jz.pojo.OrderItem;

import java.util.List;

/**
 * @param
 * @description
 * @Created by IntelliJ IDEA.
 * @author:
 * @Date: 2020/4/3
 * @return
 */
public interface OrderService {
    public String createOrder(Cart cart, Integer userId);
    /**
     * 我的订单列表
     *
     * @param userId
     *            用户id号
     * @return 返回用户的订单信息
     * @throws Exception
     */
    public List<Order> queryMyOrders(int userId) throws Exception;
    /**
     * 订单详情
     *
     * @param orderId
     *            要查询的订单号
     * @return 订单详细
     * @throws Exception
     */
    public List<OrderItem> queryOrderItems(String orderId) throws Exception;

    /**
     * 管理员--查看所有订单
     * @throws Exception
     */
    public List<Order> queryAllOrders() throws Exception;

    /**
     * 确认发货
     *
     * @param orderId
     *            发货的订单号
     * @throws Exception
     */
    public void sendOrder(String orderId) throws Exception;

    /**
     * 用户确认收货
     *
     * @param orderId
     *            收到的订单号
     * @throws Exception
     */
    public void receivedOrder(String orderId) throws Exception;
}
