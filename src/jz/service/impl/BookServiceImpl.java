package jz.service.impl;

import jz.dao.BookDao;
import jz.dao.impl.BookDaoImpl;
import jz.pojo.Book;
import jz.pojo.Page;
import jz.service.BookService;

import java.util.List;

/**
 * @param
 * @description
 * @Created by IntelliJ IDEA.
 * @author:
 * @Date: 2020/3/26
 * @return
 */
public class BookServiceImpl implements BookService {

    private BookDao bookDao = new BookDaoImpl();

    @Override
    public Page<Book> page(int pageNum, int pageSize) {
        Page<Book> page = new Page<Book>();

        page.setPageSize(pageSize);
        //总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCount();
        //设置总记录数
        page.setPageTotalCount(pageTotalCount);
        //求总页码
        Integer pageToatal = pageTotalCount/pageSize;
        if (pageTotalCount % pageSize != 0){
            pageToatal+=1;
        }
        //设置总页码
        page.setPageTotal(pageToatal);

        //设置当前页码
        page.setPageNum(pageNum);
        //求当前页面的开始索引
        int begin = (page.getPageNum()-1)*pageSize;
        //求当前页面数据
        List<Book> items = bookDao.queryForPageItems(begin,pageSize);
        //设置
        page.setItems(items);
        return page;
    }

    @Override
    public Page<Book> pageByPrice(int pageNum, int pageSize, int min, int max) {
        Page<Book> page = new Page<Book>();
        //每页显示的数量
        page.setPageSize(pageSize);
        //总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCountByPrice(min,max);
        //设置总记录数
        page.setPageTotalCount(pageTotalCount);
        //求总页码
        Integer pageToatal = pageTotalCount/pageSize;
        if (pageTotalCount % pageSize != 0){
            pageToatal+=1;
        }
        //设置总页码
        page.setPageTotal(pageToatal);

        //设置当前页码
        page.setPageNum(pageNum);
        //求当前页面的开始索引
        int begin = (page.getPageNum()-1)*pageSize;
        //求当前页面数据
        List<Book> items = bookDao.queryForPageItemsByPrice(begin,pageSize,min,max);
        //设置
        page.setItems(items);
        return page;
    }

    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookDao.deleteBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }
}
