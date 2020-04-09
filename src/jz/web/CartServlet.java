package jz.web;

import com.google.gson.Gson;
import jz.pojo.Book;
import jz.pojo.Cart;
import jz.pojo.CartItem;
import jz.service.BookService;
import jz.service.impl.BookServiceImpl;
import jz.util.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @param
 * @description
 * @Created by IntelliJ IDEA.
 * @author: ${jz}
 * @Date: 2020/4/1
 * @return
 */
@WebServlet(name = "CartServlet",urlPatterns = "/CartServlet")
public class CartServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();
    /**
     * 修改商品数量
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        // 获取请求的参数 商品编号 、商品数量
        int id = WebUtils.parseInt(req.getParameter("id"),0);
        int count = WebUtils.parseInt(req.getParameter("count"), 1);
        // 获取Cart购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");

        if (cart != null) {
            // 修改商品数量
            cart.updateCount(id,count);
            // 重定向回原来购物车展示页面
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }
    protected void ajaxAddItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //System.out.println("加入购物车");
        //System.out.println(requestuest.getParameter("id"));
        // 获取请求的参数 商品编号
        int id = WebUtils.parseInt(request.getParameter("id"), 0);
        // 调用bookService.queryBookById(id):Book得到图书的信息
        Book book = bookService.queryBookById(id);
        // 把图书信息，转换成为CartItem商品项
        CartItem cartItem = new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());
        // 调用Cart.addItem(CartItem);添加商品项
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            request.getSession().setAttribute("cart",cart);
        }
        cart.addItem(cartItem);

        System.out.println(cart);
        System.out.println("请求头Referer的值：" + request.getHeader("Referer"));
        // 最后一个添加的商品名称
        request.getSession().setAttribute("lastName", cartItem.getName());

        // 将更新数据封装成json以响应流的形式传回
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("totalCount",cart.getTotalCount());
        resultMap.put("lastName",cartItem.getName());
        Gson gson = new Gson();
        String jsonString = gson.toJson(resultMap);
        response.getWriter().write(jsonString);

    }
        protected void addItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //System.out.println("加入购物车");
        //System.out.println(requestuest.getParameter("id"));
        // 获取请求的参数 商品编号
        int id = WebUtils.parseInt(request.getParameter("id"), 0);
        // 调用bookService.queryBookById(id):Book得到图书的信息
        Book book = bookService.queryBookById(id);
        // 把图书信息，转换成为CartItem商品项
        CartItem cartItem = new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());
        // 调用Cart.addItem(CartItem);添加商品项
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            request.getSession().setAttribute("cart",cart);
        }
        cart.addItem(cartItem);

        System.out.println(cart);
        System.out.println("请求头Referer的值：" + request.getHeader("Referer"));
        // 最后一个添加的商品名称
        request.getSession().setAttribute("lastName", cartItem.getName());

        // 重定向回原来商品所在的地址页面
        // 重定向回原来商品所在的地址页面
        response.sendRedirect(request.getHeader("Referer"));
    }


    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        // 获取商品编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        // 获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");

        if (cart != null) {
            // 删除 了购物车商品项
            cart.deleteItem(id);
            // 重定向回原来购物车展示页面
            resp.sendRedirect(req.getHeader("Referer"));
        }

    }
    /**
     * 清空购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        // 1 获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
            // 清空购物车
            cart.clear();
            // 重定向回原来购物车展示页面
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request,response);
    }
}
