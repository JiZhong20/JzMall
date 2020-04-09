package jz.web;

import jz.pojo.Book;
import jz.pojo.Page;
import jz.service.BookService;
import jz.service.impl.BookServiceImpl;
import jz.util.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @param
 * @description
 * @Created by IntelliJ IDEA.
 * @author: ${jz}
 * @Date: 2020/3/29
 * @return
 */
@WebServlet(name = "ClientBookServlet",urlPatterns = "/client/ClientBookServlet")
public class ClientBookServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();
    //分页功能
    protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("经过前台");
        //1.获取请求的参数pageNum,pageSize
        int pageNum = WebUtils.parseInt(request.getParameter("pageNum"), 1);
        int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        //2.调用bookservice.page(pageNum,pageSize):page
        Page<Book> page = bookService.page(pageNum, pageSize);
        page.setUrl("client/ClientBookServlet?action=page");

        //3.保存page对象到request域中
        request.setAttribute("page",page);
        //4.请求转发到pages/manager/book-manager
        request.getRequestDispatcher("/pages/client/index.jsp").forward(request,response);

    }
    protected void pageByPrice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("经过前台");
        //1.获取请求的参数pageNum,pageSize
        int pageNum = WebUtils.parseInt(request.getParameter("pageNum"), 1);
        int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        int min = WebUtils.parseInt(request.getParameter("min"), 0);
        int max = WebUtils.parseInt(request.getParameter("max"), Integer.MAX_VALUE);

        //2.调用bookservice.page(pageNum,pageSize):page
        Page<Book> page = bookService.pageByPrice(pageNum, pageSize,min,max);

        StringBuilder stringBuilder = new StringBuilder("client/ClientBookServlet?action=pageByPrice");
        if (request.getParameter("min")!=null){
            stringBuilder.append("&min=").append(request.getParameter("min"));
        }
        if (request.getParameter("max")!=null){
            stringBuilder.append("&max=").append(request.getParameter("max"));
        }
        page.setUrl(stringBuilder.toString());

        //3.保存page对象到request域中
        request.setAttribute("page",page);
        //4.请求转发到pages/manager/book-manager
        request.getRequestDispatcher("/pages/client/index.jsp").forward(request,response);

    }
}
