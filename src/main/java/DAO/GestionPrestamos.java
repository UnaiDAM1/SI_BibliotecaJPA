package DAO;

import DTO.Ejemplar;
import DTO.Prestamo;
import Service.EjemplarService;
import Service.PrestamoService;
import Service.UsuarioService;
import Validaciones.Validaciones;

import java.time.LocalDate;

/*
 * @Autor: Unai Nieto DAM2
 *
 * Clase que se encrga de la gestión de los prestamos
 * */

public class GestionPrestamos {
    PrestamoService prestamoService;
    EjemplarService ejemplarService;
    UsuarioService usuarioService;
    Validaciones validar = new Validaciones();

    public GestionPrestamos(PrestamoService prestamoService, EjemplarService ejemplarService, UsuarioService usuarioService) {
        this.prestamoService = prestamoService;
        this.ejemplarService = ejemplarService;
        this.usuarioService = usuarioService;
    }

    // Método para hacer un prestamo
    public void hacerPrestamos(Prestamo prestamo) throws Exception {
        if (validar.estaPenalizado(prestamo.getUsuario())) {
            if (validar.numeroDePrestamos(prestamo.getUsuario(), prestamoService.listPrestamo())) {
                if (validar.ejemplarDisponible(prestamo.getEjemplar())) {
                    prestamoService.insertPrestamo(prestamo);
                    // Tras hacer un prestamo actualizamos el estado del libro prestado a "Prestado"
                    Ejemplar e = prestamo.getEjemplar();
                    e.setEstado("Prestado");
                    ejemplarService.updateEjemplar(e);
                } else {
                    throw new Exception("Ejemplar no disponible.");
                }
            } else {
                throw new Exception("Número de prestamos máximo alcanzado.");
            }
        } else {
            throw new Exception("Usuario está penalizado hasta " + prestamo.getUsuario().getPenalizacionHasta() + ".");
        }
    }

    // Método para devolver un prestamo
    public void devolverPrestamo(Prestamo prestamo) throws Exception {

        // Cambiamos el estado del libro devuelto a "Disponible"
        prestamo.getEjemplar().setEstado("Disponible");
        ejemplarService.updateEjemplar(prestamo.getEjemplar());

        // Cambiamos la fecha de devolución del prestamo a la actual
        prestamo.setFechaDevolucion(LocalDate.now());
        prestamoService.updatePrestamo(prestamo);

        // Validamos que la fecha de devolución no sea superior a 15 días después de la fecha de inicio del prestamo
        if (prestamo.getFechaInicio().plusDays(15).isBefore(LocalDate.now())) {

                LocalDate penalizacion = prestamo.getUsuario().getPenalizacionHasta();
                if (penalizacion == null) {
                    prestamo.getUsuario().setPenalizacionHasta(LocalDate.now().plusDays(15));
                } else {
                    prestamo.getUsuario().setPenalizacionHasta(penalizacion.plusDays(15));
                }
            // Si el usuario tiene una penalización superior a 45 días no se le aplicará penalización
            if (validar.penalizacionNoSuperior45(prestamo.getUsuario())) {
                usuarioService.updateUsuario(prestamo.getUsuario());
            }
        }

    }
}
