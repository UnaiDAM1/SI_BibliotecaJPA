package DTO;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

/*
 * @Autor: Unai Nieto DAM2
 *
 * */

@Entity
@Table(name = "prestamo", schema = "bibliotecajpa")
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.PERSIST)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "usuario_id", nullable = false)
    private DTO.Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.PERSIST)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "ejemplar_id", nullable = false)
    private Ejemplar ejemplar;

    @Column(name = "fechaInicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fechaDevolucion")
    private LocalDate fechaDevolucion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DTO.Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(DTO.Usuario usuario) {
        this.usuario = usuario;
    }

    public Ejemplar getEjemplar() {
        return ejemplar;
    }

    public void setEjemplar(Ejemplar ejemplar) {
        this.ejemplar = ejemplar;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public Prestamo() {
    }

    public Prestamo(Usuario usuario, Ejemplar ejemplar) {
        this.usuario = usuario;
        this.ejemplar = ejemplar;
        this.fechaInicio = LocalDate.now();
        this.fechaDevolucion = null ;
    }

    @Override
    public String toString() {
        return "Prestamo: " +
                "ID = " + id +
                "\n" + usuario +
                "\n" + ejemplar +
                "\nFecha de inicio=" + fechaInicio +
                ", fecha de devolucion=" + fechaDevolucion
                + "\n-----------------------------\n";
    }
}