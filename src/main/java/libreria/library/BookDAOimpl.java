package main.java.libreria.library;


import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BookDAOimpl implements BookDAO {
    NamedParameterJdbcTemplate template;

    public BookDAOimpl(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<Book> findAll() {
        return template.query("select * from libri", new BookRowMapper());
    }

    public List<Book> findBook(String titolo) {
        final String sql = "select * from libri where titolo=:titolo";
        SqlParameterSource param = new MapSqlParameterSource().addValue("titolo", titolo);
        return template.query(sql, param, new ResultSetExtractor<List<Book>>() {

            @Override
            public List<Book> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Book> tempList = new ArrayList<Book>();
                while (rs.next()) {

                    Book book = new Book(rs.getString("titolo"), rs.getString("autore"),
                            rs.getInt("annoPB"), rs.getString("link"));
                    tempList.add(book);
                    break;
                }
                return tempList;
            }
        });
    }

    @Override
    public void insertBook(Book book) {
        final String sql = "insert into libri (titolo , autore, annoPB, link) values(:titolo,:autore,:annoPB,:link)";
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("titolo", book.getTitolo())
                .addValue("autore", book.getAutore())
                .addValue("annoPB", book.getAnnoPB())
                .addValue("link", book.getLink());
        template.update(sql, param, holder);
    }

    @Override
    public void updateBook(Book book) {
        final String sql = "update libri set titolo=:titolo, autore=:autore, annoPB=:annoPB, link=:link, where titolo=:titolo";
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("titolo", book.getTitolo())
                .addValue("autore", book.getAutore())
                .addValue("annoPB", book.getAnnoPB())
                .addValue("link", book.getLink());
        template.update(sql, param, holder);
    }

    @Override
    public void executeUpdateBook(Book book) {
        final String sql = "update libri set titolo=:titolo, autore=:autore, annoPB=:annoPB, link=:link, where titolo=:titolo";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("titolo", book.getTitolo());
        map.put("autore", book.getAutore());
        map.put("annoPB", book.getAnnoPB());
        map.put("link", book.getLink());


        template.execute(sql, map, new PreparedStatementCallback<Object>() {
            @Override
            public Object doInPreparedStatement(PreparedStatement ps)
                    throws SQLException, DataAccessException {
                return ps.executeUpdate();
            }
        });
    }

    @Override
    public void deleteBook(Book book) {
        final String sql = "delete from libri where titolo=:titolo";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("titolo", book.getTitolo());
        template.execute(sql, map, new PreparedStatementCallback<Object>() {
            @Override
            public Object doInPreparedStatement(PreparedStatement ps)
                    throws SQLException, DataAccessException {
                return ps.executeUpdate();
            }
        });
    }


}


