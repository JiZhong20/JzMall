package jz.web;

import jz.pojo.Cart;
import jz.pojo.Order;
import jz.pojo.User;
import jz.service.OrderService;
import jz.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @param
 * @description
 * @Created by IntelliJ IDEA.
 * @author: ${jz}
 * @Date: 2020/4/3
 * @return
 */
@WebServlet(name = "OrderServlet",urlPatterns = "/OrderServlet")
public class OrderServlet extends BaseServlet {
    private OrderService orderService = new OrderServiceImpl();

    /**
     * 生成订单
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 先获取Cart购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        // 获取Userid
        User loginUser = (User) req.getSession().getAttribute("user");

        if (loginUser == null) {
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        }

        Integer userId = loginUser.getId();
//        调用orderService.createOrder(Cart,Userid);生成订单
        String orderId = orderService.createOrder(cart, userId);



//        req.setAttribute("orderId", orderId);
        // 请求转发到/pages/cart/checkout.jsp
//        req.getRequestDispatcher("/pages/cart/checksout.jsp").forward(req, resp);

        req.getSession().setAttribute("orderId",orderId);

        resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");
    }



    /**
     * 我的订单
     */
    protected void myOrders(HttpServletRequest request, HttpServletResponse response)
            throws Exception, IOException {
        User user = (User) request.getSession().getAttribute("user");
        // 用户未登录，需要先登录
        if (user == null) {
            // 如果用户没有登录，重定向到登录页面
            response.sendRedirect(request.getContextPath() + "/pages/user/login.jsp");
        } else {
            // 查询用户的订单信息
            List<Order> orders = orderService.queryMyOrders(user.getId());
            // 设置订单到域对象中
            request.getSession().setAttribute("myOrders", orders);
            // 转发到订单页面
            request.getRequestDispatcher("/pages/order/order.jsp").forward(request, response);
        }
    }

    /**
     * 确认收货
     */
    protected void receivedOrder(HttpServletRequest request, HttpServletResponse response)
            throws Exception, IOException {
        // 获取发货的订单号
        String orderId = request.getParameter("orderId");
        // 发货
        orderService.receivedOrder(orderId);

        // 重定向到订单页面
        response.sendRedirect(request.getHeader("referer"));
    }

}
