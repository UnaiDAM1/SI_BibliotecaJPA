package Service;

import DAO.LibroDAO;
import DTO.Libro;

import java.util.ArrayList;
import java.util.List;

public class LibroService {
    LibroDAO libroDAO;
    List<Libro> libros = new ArrayList<>();

    public LibroService(LibroDAO libroDAO) {
        this.libroDAO = libroDAO;
        sincronizar();
    }

    public void sincronizar() {
        libros = libroDAO.getLibros();
    }

    public void insertLibro(Libro libro) {
        libroDAO.insertLibro(libro);
        sincronizar();
    }

    public void updateLibro(Libro libro) {
        libroDAO.updateLibro(libro);
        sincronizar();
    }

    public void deleteLibro(String isbn) {
        libroDAO.deleteLibro(isbn);
        sincronizar();
    }

    public List<Libro> getLibros() {
        return libros;
    }
}
