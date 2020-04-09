package jz.dao;

import jz.pojo.Book;

import java.util.List;

/**
 * @param
 * @description
 * @Created by IntelliJ IDEA.
 * @author:
 * @Date: 2020/3/26
 * @return
 */
public interface BookDao {
     int addBook(Book book);
     int deleteBookById(Integer id);
     int updateBook(Book book);
     Book queryBookById(Integer id);
     List<Book> queryBooks();
     Integer queryForPageTotalCount();
     List<Book> queryForPageItems(int begin,int pageSize);

    Integer queryForPageTotalCountByPrice(int min, int max);

     List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max);
}
