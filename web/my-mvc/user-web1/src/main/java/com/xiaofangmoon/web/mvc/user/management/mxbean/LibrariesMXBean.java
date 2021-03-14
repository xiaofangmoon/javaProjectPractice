package com.xiaofangmoon.web.mvc.user.management.mxbean;

import java.util.List;

public interface LibrariesMXBean {
    String getName();

    void setName(String name);

    int getTotal();

    List<Book> getBookList();

    Book getBook(int index);

    void clearBookList();

}