package jz.web;

import com.google.gson.Gson;
import jz.pojo.User;
import jz.service.UserService;
import jz.service.impl.UserServiceImpl;
import jz.util.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @param
 * @description
 * @Created by IntelliJ IDEA.
 * @author: ${jz}
 * @Date: 2020/3/25
 * @return
 */
@WebServlet(name = "UserServlet",urlPatterns = "/UserServlet")
public class UserServlet extends BaseServlet {
    private UserService userService = new UserServiceImpl();

    protected void ajaxExistUsername(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        boolean existUsername = userService.existUsername(username);
        Map<Object, Object> resultMap = new HashMap<>();
        resultMap.put("existUsername",existUsername);
        Gson gson= new Gson();
        String json = gson.toJson(resultMap);
        response.getWriter().write(json);
    }



        protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("重定向");
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath());

    }
        //处理login请求
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            request.getSession().setAttribute("user",loginUser);
            request.getRequestDispatcher("/pages/user/login_success.jsp").forward(request,response);
        }
    }
    //处理regist请求
    protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String token  =(String) request.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        request.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        //1.获取请求的参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String code = request.getParameter("code");
        //测试Bean注入

        User user =  WebUtils.copyParamToBean(request.getParameterMap(),new User());
        System.out.println(user);
        //2.验证码是否正确(gugeyanzhengma)
        if (token!=null&&token.equalsIgnoreCase(code)){
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
            request.setAttribute("username",username);
            request.setAttribute("email",email);
            request.getRequestDispatcher("/pages/user/regist.jsp").forward(request,response);
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request,response);
    }
}
