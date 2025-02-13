/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
    @EJB
    private ProgramFacade pf;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BatchFacade() {
        super(Batch.class);
    }

    /**
     * @param programId the batch program
     * @param entranceYear entrance year of the batch
     * @return a Unique batch instance that registered on the given entrance
     * year.
     */
    public Batch findBatch(Integer programId, String entranceYear) {
        try {
            return (Batch) getEntityManager().createNamedQuery("Batch.findByProgram_and_EntranceYear", Batch.class)
                    .setParameter("programId", programId)
                    .setParameter("entranceYear", entranceYear).getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    /**
     * @param startDate the first date
     * @param endDate the end date
     * @return a List of Batch entities that are active in the specified range
     * date.
     */
    public List<Batch> findOverlappedBatches(Date startDate, Date endDate) {
        return (List<Batch>) getEntityManager().createNamedQuery("Batch.findBatchesByDates").setParameter("startDate", startDate).setParameter("endDate", endDate).getResultList();
    }

}
