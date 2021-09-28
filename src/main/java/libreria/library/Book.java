package main.java.libreria.library;

import lombok.Data;


@Data
public class Book {

    String titolo;
    String autore;
    Integer annoPB;
    String link;

    public Book() {
    }

    ;

    public Book(String titolo, String autore, Integer annoPB, String link) {
        this.titolo = titolo;
        this.autore = autore;
        this.annoPB = annoPB;
        this.link = link;
    }
}
