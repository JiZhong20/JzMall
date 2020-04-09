package jz.service;

import jz.pojo.Book;
import jz.pojo.Page;

import java.util.List;

/**
 * @param
 * @description
 * @Created by IntelliJ IDEA.
 * @author:
 * @Date: 2020/3/26
 * @return
 */
public interface BookService {
    void addBook(Book book);
    void deleteBookById(Integer id);
    void updateBook(Book book);
    Book queryBookById(Integer id);
    List<Book> queryBooks();
    Page<Book> page(int pageNum,int pageSize);

    Page<Book> pageByPrice(int pageNum, int pageSize, int min, int max);
}
