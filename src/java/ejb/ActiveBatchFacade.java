/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.ActiveBatch;
import model.util.Semester;

/**
 *
 * @author Amanuel
 */
@Stateless
public class ActiveBatchFacade extends AbstractFacade<ActiveBatch> {

    @PersistenceContext(unitName = "Peanu3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ActiveBatchFacade() {
        super(ActiveBatch.class);
    }
    
    /**
     * @param semester 
     * @return a List of Valid Active Batches (According to the semester valid Admission Types).
     */
    
    private List<ActiveBatch> findActiveBatches(Semester semester) {
        return null;
        //return (List<ActiveBatch>) getEntityManager().createNamedQuery("ActiveBatch.findByAdmissionTypes").setParameter("admissionTypes", semester.getValidAdmissionTypes()).getResultList();
    }
    
}
