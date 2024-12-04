package Service;

import DAO.PrestamoDAO;
import DTO.Prestamo;

import java.util.ArrayList;
import java.util.List;

public class PrestamoService {
    private PrestamoDAO prestamoDAO;
    private List<Prestamo> prestamos = new ArrayList<>();

    public PrestamoService(PrestamoDAO prestamoDAO, List<Prestamo> prestamos) {
        this.prestamoDAO = prestamoDAO;
        this.prestamos = prestamos;
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

    public List<Prestamo> listPrestamo(){
        return prestamos;
    }
}
