package Service;

import DAO.LibroDAO;
import DTO.Libro;

import java.util.ArrayList;
import java.util.List;

public class LibroService {
    LibroDAO libroDAO;
    List<Libro> libros = new ArrayList<>();

    public LibroService(LibroDAO libroDAO, List<Libro> libros) {
        this.libroDAO = libroDAO;
        this.libros = libros;
    }

    public void sincronizar() {
        libros = libroDAO.getLibros();
    }
}
