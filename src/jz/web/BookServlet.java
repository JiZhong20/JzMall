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
import java.util.List;

/**
 * @param
 * @description
 * @Created by IntelliJ IDEA.
 * @author: ${jz}
 * @Date: 2020/3/26
 * @return
 */
@WebServlet(name="BookServlet",urlPatterns ="/manage/BookServlet")
public class BookServlet extends BaseServlet {

    private BookService bookService = new BookServiceImpl();

    //分页功能
    protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取请求的参数pageNum,pageSize
        int pageNum = WebUtils.parseInt(request.getParameter("pageNum"), 1);
        int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        //2.调用bookservice.page(pageNum,pageSize):page
        Page<Book> page = bookService.page(pageNum, pageSize);
        page.setUrl("manage/BookServlet?action=page");
        //3.保存page对象到request域中
        request.setAttribute("page",page);
        //4.请求转发到pages/manager/book-manager
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);

    }
    //增删改查
    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNum = WebUtils.parseInt(request.getParameter("pageNum"),0);
        pageNum+=1;
        Book book = WebUtils.copyParamToBean(request.getParameterMap(), new Book());
        bookService.addBook(book);
        response.sendRedirect(request.getContextPath()+"/manage/BookServlet?action=page&pageNum="+ pageNum);
    }
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = WebUtils.parseInt(request.getParameter("id"),0);
        bookService.deleteBookById(id);
        response.sendRedirect(request.getContextPath()+"/manage/BookServlet?action=page&pageNum="+request.getParameter("pageNum"));
    }
    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Book book = WebUtils.copyParamToBean(request.getParameterMap(), new Book());
        bookService.updateBook(book);
        response.sendRedirect(request.getContextPath()+"/manage/BookServlet?action=page&pageNum="+request.getParameter("pageNum"));
    }

    protected void getBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int id = WebUtils.parseInt(request.getParameter("id"),0);
    Book book = bookService.queryBookById(id);
    request.setAttribute("book",book);
    request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request,response);
    }
    //获得所有book数据，封装list后让管理界面遍历输出
    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //调用
        List<Book> books = bookService.queryBooks();
        //保存到request域
        request.setAttribute("books",books);
        //请求转发到相关页面
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);
    }

}
