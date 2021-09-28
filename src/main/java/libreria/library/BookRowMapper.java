package main.java.libreria.library;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRowMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet rs, int arg1) throws SQLException {
        Book book = new Book();
        book.setTitolo(rs.getString("titolo"));
        book.setAutore(rs.getString("autore"));
        book.setAnnoPB(rs.getInt("annoPB"));
        book.setLink(rs.getString("link"));

        return book;
    }
}
