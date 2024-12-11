package DTO;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/*
 * @Autor: Unai Nieto DAM2
 *
 * */

@Entity
@Table(name = "ejemplar", schema = "bibliotecajpa")
public class Ejemplar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.PERSIST)
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

    public Ejemplar(Libro isbn) {
        this.isbn = isbn;
        this.estado = "Disponible";
    }

    @Override
    public String toString() {
        return "Ejemplar: " +
                "ID = " + id +
                "\n" + isbn +
                ", estado = '" + estado
                + "\n-----------------------------\n";
    }
}