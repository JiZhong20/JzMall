package jz.test;

import jz.dao.BookDao;
import jz.dao.impl.BookDaoImpl;
import jz.pojo.Book;
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
public class BookDaoTest {
    private BookDao bookDao = new BookDaoImpl();

    @Test
    public void queryForPageTotalCount() {
        System.out.println(bookDao.queryForPageTotalCount());
    }
    @Test
    public void queryForPageTotalCountByPrice() {
        System.out.println(bookDao.queryForPageTotalCountByPrice(10,50));
    }

    @Test
    public void queryForPageItems() {
        for (Book book:bookDao.queryForPageItems(8,4)){
            System.out.println(book);
        }
    }
    @Test
    public void queryForPageItemsByPrice() {
        for (Book book:bookDao.queryForPageItemsByPrice(1,4,10,50)){
            System.out.println(book);
        }
    }
    @Test
    public void addBook() {
        bookDao.addBook(new Book(null,"归去来兮辞","陶渊明",
                new BigDecimal(35),100,
                500,null));
    }

    @Test
    public void deleteBookById() {

    }

    @Test
    public void updateBook() {
        bookDao.updateBook(new Book(21,"归去来兮辞","陶渊明",
                new BigDecimal(15),200,
                300,null));
    }



    @Test
    public void queryBookById() {

        System.out.println(bookDao.queryBookById(21));
    }

    @Test
    public void queryBooks() {
        System.out.println(bookDao.queryBooks());
    }
}