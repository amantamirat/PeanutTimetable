/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Batch;

/**
 *
 * @author Amanuel
 */
@Stateless
public class BatchFacade extends AbstractFacade<Batch> {

    @PersistenceContext(unitName = "Peanu3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BatchFacade() {
        super(Batch.class);
    }
    
    /**
     * @param startDate the first date
     * @param endDate the end date
     * @return a List of Batch entities that are active in the specified range date.
     */
    public List<Batch> findOverlappedBatches(Date startDate, Date endDate) {
        return (List<Batch>) getEntityManager().createNamedQuery("Batch.findBatchesByDates").setParameter("startDate", startDate).setParameter("endDate", endDate).getResultList();
    }  
    
    
    
    
}
