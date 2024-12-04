package DAO;

import DTO.Prestamo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class PrestamoDAOImpl implements PrestamoDAO {
    EntityManager em;

    public PrestamoDAOImpl(EntityManager em) {
        this.em = em;
    }


    @Override
    public void insertPrestamo(Prestamo p) {
        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
    }

    @Override
    public void updatePrestamo(Prestamo p) {
        em.getTransaction().begin();
        em.merge(p);
        em.getTransaction().commit();
    }

    @Override
    public void deletePrestamo(int id) {
        em.getTransaction().begin();
        Prestamo p = em.find(Prestamo.class, id);
        if (p != null) {
            em.remove(p);
        }
        em.getTransaction().commit();
    }

    @Override
    public List<Prestamo> listPrestamo() {
        TypedQuery<Prestamo> query = em.createQuery("SELECT p FROM Prestamo p", Prestamo.class);
        return query.getResultList();
    }
}

