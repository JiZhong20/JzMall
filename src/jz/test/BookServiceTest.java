package jz.test;

import jz.pojo.Book;
import jz.service.BookService;
import jz.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @param
 * @description
 * @Created by IntelliJ IDEA.
 * @author:
 * @Date: 2020/3/26
 * @return
 */
public class BookServiceTest {
    private BookService bookService = new BookServiceImpl();

    @Test
    public void page(){
        System.out.println(bookService.page(1,4));
    }
    @Test
    public void pageByPrice(){
        System.out.println(bookService.pageByPrice(1,4,10,50));
    }


    @Test
    public void addBook() {
        bookService.addBook(new Book(null,"瓦尓登湖","梭罗",
                new BigDecimal(35),100,
                500,null));
    }

    @Test
    public void deleteBookById() {
    }

    @Test
    public void updateBook() {
        bookService.updateBook(new Book(22,"瓦尓登湖","梭罗",
                new BigDecimal(30),500,
                1000,null));
    }

    @Test
    public void queryBookById() {
    }

    @Test
    public void queryBooks() {
    }

}