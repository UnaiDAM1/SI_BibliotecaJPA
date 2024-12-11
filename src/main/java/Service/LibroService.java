package Service;

import DAO.LibroDAO;
import DTO.Libro;
import Validaciones.Validaciones;

import java.util.ArrayList;
import java.util.List;

/*
 * @Autor: Unai Nieto DAM2
 *
 * */

public class LibroService {
    LibroDAO libroDAO;
    List<Libro> libros = new ArrayList<>();
    Validaciones validar = new Validaciones();

    public LibroService(LibroDAO libroDAO) {
        this.libroDAO = libroDAO;
        sincronizar();
    }

    public void sincronizar() {
        libros = libroDAO.getLibros();
    }

    public void insertLibro(Libro libro) throws Exception {
        if (validar.validarISBN13(libro.getIsbn())) {
            libroDAO.insertLibro(libro);
            sincronizar();
        } else {
            throw new Exception("ISBN invalido.");
        }

    }

    public void updateLibro(Libro libro) throws Exception {
        if (validar.validarISBN13(libro.getIsbn())) {
            libroDAO.updateLibro(libro);
            sincronizar();
        } else {
            throw new Exception("ISBN invalido.");
        }

    }

    public void deleteLibro(String isbn) {
        libroDAO.deleteLibro(isbn);
        sincronizar();
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public Libro getLibroPorISBN(String isbn){
        return libroDAO.getLibroPorISBN(isbn);
    }
}
