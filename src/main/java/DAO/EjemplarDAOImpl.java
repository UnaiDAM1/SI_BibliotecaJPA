package DAO;

import DTO.Ejemplar;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class EjemplarDAOImpl implements EjemplarDAO {
    EntityManager em;

    public EjemplarDAOImpl(EntityManager em) {
        this.em = em;
    }


    @Override
    public void insertEmplar(Ejemplar ejemplar) {
        em.getTransaction().begin();
        em.persist(ejemplar);
        em.getTransaction().commit();
    }

    @Override
    public void updateEmplar(Ejemplar ejemplar) {
        em.getTransaction().begin();
        em.merge(ejemplar);
        em.getTransaction().commit();
    }

    @Override
    public void deleteEmplar(int id) {
        em.getTransaction().begin();
        Ejemplar ejemplar = em.find(Ejemplar.class, id);
        if (ejemplar != null) {
            em.remove(ejemplar);
        }
        em.getTransaction().commit();
    }

    @Override
    public List<Ejemplar> listEmplares() {
        TypedQuery<Ejemplar> query = em.createQuery("SELECT e FROM Ejemplar e", Ejemplar.class);
        return query.getResultList();
    }
}
