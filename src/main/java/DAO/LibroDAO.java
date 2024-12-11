package DAO;

import DTO.Libro;

import java.util.List;

/*
 * @Autor: Unai Nieto DAM2
 *
 * */

public interface LibroDAO {
    public void insertLibro(Libro libro);
    public void updateLibro(Libro libro);
    public void deleteLibro(String isbn);
    public List<Libro> getLibros();
    public Libro getLibroPorISBN(String isbn);
}
