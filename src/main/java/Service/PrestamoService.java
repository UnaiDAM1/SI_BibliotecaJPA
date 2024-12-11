package Service;

import DAO.PrestamoDAO;
import DTO.Prestamo;

import java.util.ArrayList;
import java.util.List;

/*
 * @Autor: Unai Nieto DAM2
 *
 * */

public class PrestamoService {
    private PrestamoDAO prestamoDAO;
    private List<Prestamo> prestamos = new ArrayList<>();

    public PrestamoService(PrestamoDAO prestamoDAO) {
        this.prestamoDAO = prestamoDAO;
        sincronizar();
    }

    public void sincronizar(){
        prestamos = prestamoDAO.listPrestamo();
    }

    public void insertPrestamo(Prestamo prestamo){
        prestamoDAO.insertPrestamo(prestamo);
        sincronizar();
    }

    public void updatePrestamo(Prestamo prestamo){
        prestamoDAO.updatePrestamo(prestamo);
        sincronizar();
    }

    public void deletePrestamo(int id){
        prestamoDAO.deletePrestamo(id);
        sincronizar();
    }
    public void deletePrestamosByLibro(String isbn){
        for (Prestamo prestamo : prestamoDAO.listPrestamo()) {
            if (prestamo.getEjemplar().getIsbn().getIsbn().equals(isbn)) {
                prestamoDAO.deletePrestamo(prestamo.getId());
            }
        }
    }

    public List<Prestamo> listPrestamo(){
        return prestamos;
    }


    public List<Prestamo> listPrestamoPorID(int id){
        return prestamoDAO.listPrestamoPorID(id);
    }

    public List<Prestamo> listPrestamosPorIDMemoria(int id) {
        List<Prestamo> prestamosDeID = new ArrayList<>();
        for (Prestamo prestamo : prestamos) {
            if (prestamo.getUsuario().getId() == id) {
                prestamosDeID.add(prestamo);
            }
        }
        return prestamosDeID;
    }
    public Prestamo getPrestamo(int id){
        return prestamoDAO.getPrestamo(id);
    }
}
