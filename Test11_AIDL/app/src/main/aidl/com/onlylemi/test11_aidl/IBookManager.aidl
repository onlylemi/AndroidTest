// IBookManager.aidl
package com.onlylemi.test11_aidl;

// Declare any non-default types here with import statements
import com.onlylemi.test11_aidl.test2.Book;

interface IBookManager {

    List<Book> getBooks();

    Book getBook();

    int getBooksCount();

    void addBook(in Book book);
}
