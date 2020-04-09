package jz.web;

import com.sun.org.apache.bcel.internal.generic.NEW;
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
 * @Date: 2020/3/23
 * @return
 */
@WebServlet(name = "RegistServlet",urlPatterns ="/RegistServlet" )
public class RegistServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取请求的参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String code = request.getParameter("code");
        //2.验证码是否正确(验证码暂时固定的)
        if ("bnbnp".equalsIgnoreCase(code)){
            if (userService.existUsername(username)){
                //true,说明用户名存在，重新注册
                System.out.println("用户名已存在");
                //把回显信息保存到request域中，（情况有多种，request域中的msg是共用的，而且有ifelse筛选所以msg值只会被set一次）
                request.setAttribute("msg","用户名已存在！");
                request.setAttribute("username",username);
                request.setAttribute("email",email);

                //跳回注册页面
                request.getRequestDispatcher("/pages/user/regist.jsp").forward(request,response);

            }else {
                //说明用户名可用，将用户信息保存到数据库中
                userService.registUser(new User(null,username,password,email));
                request.getRequestDispatcher("/pages/user/regist_success.jsp").forward(request,response);

            }
        }else {
            //不正确，调回注册页面
            System.out.println("验证码错误！");
            request.setAttribute("msg","验证码错误！");
            request.setAttribute("username","username");
            request.setAttribute("email","email！");
            request.getRequestDispatcher("/pages/user/regist.jsp").forward(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
