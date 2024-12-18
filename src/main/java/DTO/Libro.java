package DTO;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

/*
 * @Autor: Unai Nieto DAM2
 *
 * */

@Entity
@Table(name = "libro", schema = "bibliotecajpa")
public class Libro {
    @Id
    @Column(name = "isbn", nullable = false, length = 20)
    private String isbn;

    @Column(name = "titulo", nullable = false, length = 200)
    private String titulo;

    @Column(name = "autor", nullable = false, length = 100)
    private String autor;

    @OneToMany(mappedBy = "isbn", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Ejemplar> ejemplars = new LinkedHashSet<>();

    public Set<Ejemplar> getEjemplars() {
        return ejemplars;
    }

    public void setEjemplars(Set<Ejemplar> ejemplars) {
        this.ejemplars = ejemplars;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Libro() {
    }

    public Libro(String isbn, String titulo, String autor) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "\nLibro: " +
                "ISBN = '" + isbn + '\'' +
                ", titulo = '" + titulo + '\'' +
                ", autor = '" + autor;
    }
}