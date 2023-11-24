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
import logica.Patient;
import persistence.exceptions.NonexistentEntityException;

/**
 *
 * @author jorge
 */
public class PatientJpaController implements Serializable {

    public PatientJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
    public PatientJpaController() {
      emf=Persistence.createEntityManagerFactory("ConsultorioOdontologico_PU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Patient patient) {
        if (patient.getListShift() == null) {
            patient.setListShift(new ArrayList<Shift>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Shift> attachedListShift = new ArrayList<Shift>();
            for (Shift listShiftShiftToAttach : patient.getListShift()) {
                listShiftShiftToAttach = em.getReference(listShiftShiftToAttach.getClass(), listShiftShiftToAttach.getIdShift());
                attachedListShift.add(listShiftShiftToAttach);
            }
            patient.setListShift(attachedListShift);
            em.persist(patient);
            for (Shift listShiftShift : patient.getListShift()) {
                Patient oldPatientOfListShiftShift = listShiftShift.getPatient();
                listShiftShift.setPatient(patient);
                listShiftShift = em.merge(listShiftShift);
                if (oldPatientOfListShiftShift != null) {
                    oldPatientOfListShiftShift.getListShift().remove(listShiftShift);
                    oldPatientOfListShiftShift = em.merge(oldPatientOfListShiftShift);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Patient patient) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Patient persistentPatient = em.find(Patient.class, patient.getId());
            List<Shift> listShiftOld = persistentPatient.getListShift();
            List<Shift> listShiftNew = patient.getListShift();
            List<Shift> attachedListShiftNew = new ArrayList<Shift>();
            for (Shift listShiftNewShiftToAttach : listShiftNew) {
                listShiftNewShiftToAttach = em.getReference(listShiftNewShiftToAttach.getClass(), listShiftNewShiftToAttach.getIdShift());
                attachedListShiftNew.add(listShiftNewShiftToAttach);
            }
            listShiftNew = attachedListShiftNew;
            patient.setListShift(listShiftNew);
            patient = em.merge(patient);
            for (Shift listShiftOldShift : listShiftOld) {
                if (!listShiftNew.contains(listShiftOldShift)) {
                    listShiftOldShift.setPatient(null);
                    listShiftOldShift = em.merge(listShiftOldShift);
                }
            }
            for (Shift listShiftNewShift : listShiftNew) {
                if (!listShiftOld.contains(listShiftNewShift)) {
                    Patient oldPatientOfListShiftNewShift = listShiftNewShift.getPatient();
                    listShiftNewShift.setPatient(patient);
                    listShiftNewShift = em.merge(listShiftNewShift);
                    if (oldPatientOfListShiftNewShift != null && !oldPatientOfListShiftNewShift.equals(patient)) {
                        oldPatientOfListShiftNewShift.getListShift().remove(listShiftNewShift);
                        oldPatientOfListShiftNewShift = em.merge(oldPatientOfListShiftNewShift);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = patient.getId();
                if (findPatient(id) == null) {
                    throw new NonexistentEntityException("The patient with id " + id + " no longer exists.");
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
            Patient patient;
            try {
                patient = em.getReference(Patient.class, id);
                patient.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The patient with id " + id + " no longer exists.", enfe);
            }
            List<Shift> listShift = patient.getListShift();
            for (Shift listShiftShift : listShift) {
                listShiftShift.setPatient(null);
                listShiftShift = em.merge(listShiftShift);
            }
            em.remove(patient);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Patient> findPatientEntities() {
        return findPatientEntities(true, -1, -1);
    }

    public List<Patient> findPatientEntities(int maxResults, int firstResult) {
        return findPatientEntities(false, maxResults, firstResult);
    }

    private List<Patient> findPatientEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Patient.class));
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

    public Patient findPatient(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Patient.class, id);
        } finally {
            em.close();
        }
    }

    public int getPatientCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Patient> rt = cq.from(Patient.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
