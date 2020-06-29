package org.jixi.control;

import org.jixi.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * 组件BookController需要用到另一个组件BookService
 */
@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    public void  printBookService(){
        System.out.println(bookService);
    }
}
