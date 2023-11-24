/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistence;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import logica.Schudele;
import persistence.exceptions.NonexistentEntityException;

/**
 *
 * @author jorge
 */
public class SchudeleJpaController implements Serializable {

    public SchudeleJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

     public SchudeleJpaController() {
      emf=Persistence.createEntityManagerFactory("ConsultorioOdontologico_PU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Schudele schudele) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(schudele);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Schudele schudele) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            schudele = em.merge(schudele);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = schudele.getIdSchudele();
                if (findSchudele(id) == null) {
                    throw new NonexistentEntityException("The schudele with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Schudele schudele;
            try {
                schudele = em.getReference(Schudele.class, id);
                schudele.getIdSchudele();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The schudele with id " + id + " no longer exists.", enfe);
            }
            em.remove(schudele);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Schudele> findSchudeleEntities() {
        return findSchudeleEntities(true, -1, -1);
    }

    public List<Schudele> findSchudeleEntities(int maxResults, int firstResult) {
        return findSchudeleEntities(false, maxResults, firstResult);
    }

    private List<Schudele> findSchudeleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Schudele.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Schudele findSchudele(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Schudele.class, id);
        } finally {
            em.close();
        }
    }

    public int getSchudeleCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Schudele> rt = cq.from(Schudele.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
