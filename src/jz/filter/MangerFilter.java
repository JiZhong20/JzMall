package jz.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @param
 * @description
 * @Created by IntelliJ IDEA.
 * @author: ${jz}
 * @Date: 2020/4/4
 * @return
 */
@WebFilter(filterName = "MangerFilter",urlPatterns = {"/pages/manager/*","/manage/BookServlet"})
public class MangerFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)req;
        Object user = httpServletRequest.getSession().getAttribute("user");
        if (user==null){
    httpServletRequest.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);

        } else{
            chain.doFilter(req, resp);
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
