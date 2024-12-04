package DAO;

import DTO.Prestamo;

import java.util.List;

public interface PrestamoDAO {
    public void insertPrestamo(Prestamo p);
    public void updatePrestamo(Prestamo p);
    public void deletePrestamo(int id);
    public List<Prestamo> listPrestamo();
}
