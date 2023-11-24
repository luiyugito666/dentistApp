/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistence;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import logica.Shift;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import logica.Dentist;
import persistence.exceptions.NonexistentEntityException;

/**
 *
 * @author jorge
 */
public class DentistJpaController implements Serializable {

    public DentistJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
   
            
    public DentistJpaController() {
      emf=Persistence.createEntityManagerFactory("ConsultorioOdontologico_PU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Dentist dentist) {
        if (dentist.getListShift() == null) {
            dentist.setListShift(new ArrayList<Shift>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Shift> attachedListShift = new ArrayList<Shift>();
            for (Shift listShiftShiftToAttach : dentist.getListShift()) {
                listShiftShiftToAttach = em.getReference(listShiftShiftToAttach.getClass(), listShiftShiftToAttach.getIdShift());
                attachedListShift.add(listShiftShiftToAttach);
            }
            dentist.setListShift(attachedListShift);
            em.persist(dentist);
            for (Shift listShiftShift : dentist.getListShift()) {
                Dentist oldDentistOfListShiftShift = listShiftShift.getDentist();
                listShiftShift.setDentist(dentist);
                listShiftShift = em.merge(listShiftShift);
                if (oldDentistOfListShiftShift != null) {
                    oldDentistOfListShiftShift.getListShift().remove(listShiftShift);
                    oldDentistOfListShiftShift = em.merge(oldDentistOfListShiftShift);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Dentist dentist) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Dentist persistentDentist = em.find(Dentist.class, dentist.getId());
            List<Shift> listShiftOld = persistentDentist.getListShift();
            List<Shift> listShiftNew = dentist.getListShift();
            List<Shift> attachedListShiftNew = new ArrayList<Shift>();
            for (Shift listShiftNewShiftToAttach : listShiftNew) {
                listShiftNewShiftToAttach = em.getReference(listShiftNewShiftToAttach.getClass(), listShiftNewShiftToAttach.getIdShift());
                attachedListShiftNew.add(listShiftNewShiftToAttach);
            }
            listShiftNew = attachedListShiftNew;
            dentist.setListShift(listShiftNew);
            dentist = em.merge(dentist);
            for (Shift listShiftOldShift : listShiftOld) {
                if (!listShiftNew.contains(listShiftOldShift)) {
                    listShiftOldShift.setDentist(null);
                    listShiftOldShift = em.merge(listShiftOldShift);
                }
            }
            for (Shift listShiftNewShift : listShiftNew) {
                if (!listShiftOld.contains(listShiftNewShift)) {
                    Dentist oldDentistOfListShiftNewShift = listShiftNewShift.getDentist();
                    listShiftNewShift.setDentist(dentist);
                    listShiftNewShift = em.merge(listShiftNewShift);
                    if (oldDentistOfListShiftNewShift != null && !oldDentistOfListShiftNewShift.equals(dentist)) {
                        oldDentistOfListShiftNewShift.getListShift().remove(listShiftNewShift);
                        oldDentistOfListShiftNewShift = em.merge(oldDentistOfListShiftNewShift);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = dentist.getId();
                if (findDentist(id) == null) {
                    throw new NonexistentEntityException("The dentist with id " + id + " no longer exists.");
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
            Dentist dentist;
            try {
                dentist = em.getReference(Dentist.class, id);
                dentist.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The dentist with id " + id + " no longer exists.", enfe);
            }
            List<Shift> listShift = dentist.getListShift();
            for (Shift listShiftShift : listShift) {
                listShiftShift.setDentist(null);
                listShiftShift = em.merge(listShiftShift);
            }
            em.remove(dentist);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Dentist> findDentistEntities() {
        return findDentistEntities(true, -1, -1);
    }

    public List<Dentist> findDentistEntities(int maxResults, int firstResult) {
        return findDentistEntities(false, maxResults, firstResult);
    }

    private List<Dentist> findDentistEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Dentist.class));
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

    public Dentist findDentist(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Dentist.class, id);
        } finally {
            em.close();
        }
    }

    public int getDentistCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Dentist> rt = cq.from(Dentist.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
