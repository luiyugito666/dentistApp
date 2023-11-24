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
import logica.Dentist;
import logica.Patient;
import logica.Shift;
import persistence.exceptions.NonexistentEntityException;

/**
 *
 * @author jorge
 */
public class ShiftJpaController implements Serializable {

    public ShiftJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
    
     public ShiftJpaController() {
      emf=Persistence.createEntityManagerFactory("ConsultorioOdontologico_PU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Shift shift) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Dentist dentist = shift.getDentist();
            if (dentist != null) {
                dentist = em.getReference(dentist.getClass(), dentist.getId());
                shift.setDentist(dentist);
            }
            Patient patient = shift.getPatient();
            if (patient != null) {
                patient = em.getReference(patient.getClass(), patient.getId());
                shift.setPatient(patient);
            }
            em.persist(shift);
            if (dentist != null) {
                dentist.getListShift().add(shift);
                dentist = em.merge(dentist);
            }
            if (patient != null) {
                patient.getListShift().add(shift);
                patient = em.merge(patient);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Shift shift) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Shift persistentShift = em.find(Shift.class, shift.getIdShift());
            Dentist dentistOld = persistentShift.getDentist();
            Dentist dentistNew = shift.getDentist();
            Patient patientOld = persistentShift.getPatient();
            Patient patientNew = shift.getPatient();
            if (dentistNew != null) {
                dentistNew = em.getReference(dentistNew.getClass(), dentistNew.getId());
                shift.setDentist(dentistNew);
            }
            if (patientNew != null) {
                patientNew = em.getReference(patientNew.getClass(), patientNew.getId());
                shift.setPatient(patientNew);
            }
            shift = em.merge(shift);
            if (dentistOld != null && !dentistOld.equals(dentistNew)) {
                dentistOld.getListShift().remove(shift);
                dentistOld = em.merge(dentistOld);
            }
            if (dentistNew != null && !dentistNew.equals(dentistOld)) {
                dentistNew.getListShift().add(shift);
                dentistNew = em.merge(dentistNew);
            }
            if (patientOld != null && !patientOld.equals(patientNew)) {
                patientOld.getListShift().remove(shift);
                patientOld = em.merge(patientOld);
            }
            if (patientNew != null && !patientNew.equals(patientOld)) {
                patientNew.getListShift().add(shift);
                patientNew = em.merge(patientNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = shift.getIdShift();
                if (findShift(id) == null) {
                    throw new NonexistentEntityException("The shift with id " + id + " no longer exists.");
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
            Shift shift;
            try {
                shift = em.getReference(Shift.class, id);
                shift.getIdShift();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The shift with id " + id + " no longer exists.", enfe);
            }
            Dentist dentist = shift.getDentist();
            if (dentist != null) {
                dentist.getListShift().remove(shift);
                dentist = em.merge(dentist);
            }
            Patient patient = shift.getPatient();
            if (patient != null) {
                patient.getListShift().remove(shift);
                patient = em.merge(patient);
            }
            em.remove(shift);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Shift> findShiftEntities() {
        return findShiftEntities(true, -1, -1);
    }

    public List<Shift> findShiftEntities(int maxResults, int firstResult) {
        return findShiftEntities(false, maxResults, firstResult);
    }

    private List<Shift> findShiftEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Shift.class));
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

    public Shift findShift(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Shift.class, id);
        } finally {
            em.close();
        }
    }

    public int getShiftCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Shift> rt = cq.from(Shift.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
