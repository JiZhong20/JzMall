package jz.web;

import jz.pojo.User;
import jz.service.UserService;
import jz.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @param
 * @description
 * @Created by IntelliJ IDEA.
 * @author: ${jz}
 * @Date: 2020/3/24
 * @return
 */
@WebServlet(name = "LoginServlet",urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //1.获取请求参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User loginUser = userService.login(new User(null, username, password, null));
        if (loginUser==null){
            //把错误信息和回显的表单信息保存到request域中
            request.setAttribute("msg","用户名或密码错误!");
            request.setAttribute("username",username);
            //重新调回登录页面
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request,response);
        }else {
            request.getRequestDispatcher("/pages/user/login_success.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
