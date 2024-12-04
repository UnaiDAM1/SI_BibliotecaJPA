package DTO;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "ejemplar", schema = "bibliotecajpa")
public class Ejemplar {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "isbn", nullable = false)
    private DTO.Libro isbn;

    @ColumnDefault("'Disponible'")
    @Lob
    @Column(name = "estado")
    private String estado;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DTO.Libro getIsbn() {
        return isbn;
    }

    public void setIsbn(DTO.Libro isbn) {
        this.isbn = isbn;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Ejemplar() {
    }

    public Ejemplar(Integer id, Libro isbn, String estado) {
        this.id = id;
        this.isbn = isbn;
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Ejemplar: " +
                "\nID = " + id +
                ", ISBN = " + isbn +
                ", estado = '" + estado;
    }
}