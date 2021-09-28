package main.java.libreria.library;

import java.util.List;

public interface BookDAO {
    List<Book> findAll();

    void insertBook(Book book);

    void updateBook(Book book);

    void executeUpdateBook(Book book);

    public void deleteBook(Book book);
}
