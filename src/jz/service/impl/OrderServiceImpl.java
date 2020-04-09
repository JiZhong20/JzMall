package jz.service.impl;

import jz.dao.BookDao;
import jz.dao.OrderDao;
import jz.dao.OrderItemDao;
import jz.dao.impl.BookDaoImpl;
import jz.dao.impl.OrderDaoImpl;
import jz.dao.impl.OrderItemDaoImpl;
import jz.pojo.*;
import jz.service.OrderService;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @param
 * @description
 * @Created by IntelliJ IDEA.
 * @author:
 * @Date: 2020/4/3
 * @return
 */
public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private BookDao bookDao = new BookDaoImpl();


    @Override
    public String createOrder(Cart cart, Integer userId) {

        String orderId = System.currentTimeMillis()+""+userId;

        Order order = new Order(orderId,new Date(),cart.getTotalPrice(),0,userId);

        orderDao.saveOrder(order);
        // 遍历购物车中每一个商品项转换成为订单项保存到数据库
        for (Map.Entry<Integer, CartItem>entry : cart.getItems().entrySet()){
            // 获取每一个购物车中的商品项
            CartItem cartItem = entry.getValue();
            // 转换为每一个订单项
            OrderItem orderItem = new OrderItem(null,cartItem.getName(),cartItem.getCount(),cartItem.getPrice(),cartItem.getTotalPrice(), orderId);
            // 保存订单项到数据库
            orderItemDao.saveOrderItem(orderItem);

            // 更新库存和销量

            Book book = bookDao.queryBookById(cartItem.getId());
            book.setSales( book.getSales() + cartItem.getCount() );
            book.setStock( book.getStock() - cartItem.getCount() );
            bookDao.updateBook(book);

        }

        // 清空购物车
        cart.clear();

        return orderId;
    }

    @Override
    public List<Order> queryMyOrders(int userId) throws Exception {
        // 查找自己的订单
        return orderDao.queryMyOrders(userId);
    }

    @Override
    public List<OrderItem> queryOrderItems(String orderId) throws Exception {
        // 查找某个订单的订单项
        return orderItemDao.queryOrderItems(orderId);
    }

    @Override
    public List<Order> queryAllOrders() throws Exception {
        // 查询所有订单
        return orderDao.queryAllOrders();
    }

    @Override
    public void sendOrder(String orderId) throws Exception {
        // 修改订单状态为已发货
        orderDao.updateOrderStatus(1, orderId);
    }

    @Override
    public void receivedOrder(String orderId) throws Exception {
        // 修改订单状态为已发货
        orderDao.updateOrderStatus(2, orderId);
    }


}
