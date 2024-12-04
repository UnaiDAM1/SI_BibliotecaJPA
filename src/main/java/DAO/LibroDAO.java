package DAO;

import DTO.Libro;

import java.util.List;

public interface LibroDAO {
    public void insertLibro(Libro libro);
    public void updateLibro(Libro libro);
    public void deleteLibro(String isbn);
    public List<Libro> getLibros();
}
