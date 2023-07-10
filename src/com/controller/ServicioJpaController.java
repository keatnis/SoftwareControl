package com.controller;

import com.controller.exceptions.NonexistentEntityException;
import com.model.Servicio;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.swing.JOptionPane;

/**
 *
 * @author keatnis
 */
public class ServicioJpaController implements Serializable {

    public ServicioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Servicio servicio) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(servicio);
            em.getTransaction().commit();
            JOptionPane.showMessageDialog(null,
                    "Servicio registrado correctamente");
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Servicio servicio) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            servicio = em.merge(servicio);
            em.getTransaction().commit();
            JOptionPane.showMessageDialog(null,
                    "Datos del servicio actualizado correctamente");
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = servicio.getId();
                if (findServicio(id) == null) {
                    JOptionPane.showMessageDialog(null,
                            "The servicio with id " + id + " no longer exists.");
                    throw new NonexistentEntityException("The servicio with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Servicio servicio;
            JOptionPane.showMessageDialog(null,
                    "Registro de servicio eliminado!");
            try {
                servicio = em.getReference(Servicio.class, id);
                servicio.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The servicio with id " + id + " no longer exists.", enfe);
            }
            em.remove(servicio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Servicio> findServicioEntities() {
        return findServicioEntities(true, -1, -1);
    }

    public List<Servicio> findServicioEntities(int maxResults, int firstResult) {
        return findServicioEntities(false, maxResults, firstResult);
    }

    private List<Servicio> findServicioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Servicio.class));
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

    public Servicio findServicio(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Servicio.class, id);
        } finally {
            em.close();
        }
    }

    public int getServicioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Servicio> rt = cq.from(Servicio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<Servicio> getServicesOrderByDate() {
        String sqlString = "SELECT s.*,v.* FROM SERVICIO s\n"
                + "INNER JOIN VEHICULO v ON s.vehiculo_id=v.vehiculo_id \n"
                + "ORDER BY s.fecha DESC;";
        EntityManager em = getEntityManager();
        List<Servicio> list = em.createNativeQuery(sqlString, Servicio.class)
                .getResultList();
        return list;

    }

    public List<Servicio> getServicesById(Integer id) {
        String sqlString = "SELECT s.*,v.* FROM SERVICIO s\n"
                + "INNER JOIN VEHICULO v ON s.vehiculo_id=v.vehiculo_id \n"
                + "WHERE s.vehiculo_id =? ORDER BY s.fecha DESC;";
        EntityManager em = getEntityManager();
        List<Servicio> list = em.createNativeQuery(sqlString, Servicio.class)
                .setParameter(1, id)
                .getResultList();
        return list;

    }

    public void updateKMByIdVehiculo(Integer idVehiculo, Float km) {
        String query = "UPDATE VEHICULO SET km_actual =? WHERE vehiculo_id =?";
        EntityManager em = getEntityManager();
        // you will always get a single result
        try {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            Integer res = em.createNativeQuery(query)
                    .setParameter(1, km)
                    .setParameter(2, idVehiculo)
                    .executeUpdate();
            System.out.println("res update " + res);
            em.persist(res);
            tx.commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public boolean servicioExits(Integer id) {
        String query = "select count(id) from SERVICIO  where id=" + id;
        final EntityManager em = getEntityManager();
        // you will always get a single result
        Long count = (Long) em.createNativeQuery(query).getSingleResult();
        return ((count.equals(0L)) ? false : true);

    }
}
