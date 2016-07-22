// IBookManager.aidl
package com.onlylemi.test11_binder.binder;

// Declare any non-default types here with import statements

import com.onlylemi.test11_binder.binder.Book;

interface IBookManager {
   List<Book> getBookList();
   void addBook(in Book book);
}
