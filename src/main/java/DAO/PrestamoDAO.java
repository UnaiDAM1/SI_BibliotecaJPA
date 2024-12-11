package DAO;

import DTO.Prestamo;

import java.util.List;

/*
 * @Autor: Unai Nieto DAM2
 *
 * */

public interface PrestamoDAO {
    public void insertPrestamo(Prestamo p);
    public void updatePrestamo(Prestamo p);
    public void deletePrestamo(int id);
    public List<Prestamo> listPrestamo();
    public List<Prestamo> listPrestamoPorID(int id);
    public Prestamo getPrestamo(int id);
}
