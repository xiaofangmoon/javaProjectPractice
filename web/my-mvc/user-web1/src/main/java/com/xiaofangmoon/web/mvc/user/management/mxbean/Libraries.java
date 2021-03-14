package com.xiaofangmoon.web.mvc.user.management.mxbean;

import lombok.ToString;

import java.util.List;

/**
 * @author xiaofang
 */
@ToString
public class Libraries implements LibrariesMXBean {
    String name;
    private List<Book> bookList;

    public Libraries(List<Book> bookList) {
        this.bookList = bookList;
    }

    @Override
    public List<Book> getBookList() {
        return bookList;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getTotal() {
        return bookList.size();
    }

    @Override
    public void clearBookList() {
        bookList.clear();
    }

    @Override
    public Book getBook(int index) {
        if (bookList.size() > 0) {
            return bookList.get(index);
        }
        return null;
    }

}