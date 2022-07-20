package com.zen.entity;

import com.sun.org.apache.bcel.internal.classfile.Code;
import com.zen.reflect.ApplicationContext;
import com.zen.service.BookService;

public class BookStrap {
    public static void main(String[] args) throws Exception {
        ApplicationContext applicationContext = new ApplicationContext();
        // applicationContext.initContext();
        applicationContext.initContextByAnnotation();
        BookService bean = (BookService) applicationContext.getBean(BookService.class);
        bean.borrow(new User(1, "刘水龙", "00"), new Book(1, "黄书"));
    }
}

