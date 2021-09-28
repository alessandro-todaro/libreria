CREATE TABLE libri
(
 titolo varchar(100) NOT NULL,
  autore varchar(100) NOT NULL,
  annoPB integer NOT NULL,
  id SERIAL PRIMARY KEY,
 link varchar(100) DEFAULT NULL
);