package jz.filter;

import jz.util.JdbcUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @param
 * @description
 * @Created by IntelliJ IDEA.
 * @author: ${jz}
 * @Date: 2020/4/5
 * @return
 */
@WebFilter(filterName = "TransactionFilter",urlPatterns = "/*")
public class TransactionFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        try {
            chain.doFilter(req, resp);
            JdbcUtils.commitAndClose();
        } catch (Exception e) {

            JdbcUtils.rollbackAndClose();
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
