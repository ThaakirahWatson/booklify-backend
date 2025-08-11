package com.booklify.service;

import com.booklify.domain.Book;

import java.util.List;

public interface IBookService extends IService<Book, Long>{
      List<Book> getAll();
      void deleteAll();
}
