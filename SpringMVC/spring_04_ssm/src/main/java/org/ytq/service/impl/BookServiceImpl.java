package org.ytq.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ytq.controller.Code;
import org.ytq.dao.BookDao;
import org.ytq.domain.Book;
import org.ytq.exception.BusinessException;
import org.ytq.exception.SystemException;
import org.ytq.service.BookService;

import java.util.List;
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao;
    public boolean save(Book book) {
        bookDao.save(book);
        return true;
    }

    public boolean update(Book book) {
        bookDao.update(book);
        return true;
    }

    public boolean delete(Integer id) {
        bookDao.delete(id);
        return true;
    }

    public Book getById(Integer id) {
        // 将可能出现的异常进行包装，转换成自定义异常
        if(id == 1){
            throw new BusinessException("请不要使用你的技术挑战我的耐性", Code.BUSINESS_ERR);
        }
//        try {
//            int i = 1 / 0;
//        } catch (ArithmeticException e) {
//            throw new SystemException("服务器访问超时，请重试！", e, Code.SYSTEM_TIMEOUT_ERR);
//        }
        return bookDao.getById(id);
    }

    public List<Book> getAll() {
        return bookDao.getAll();
    }
}
