/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.SectionClass;

/**
 *
 * @author Amanuel
 */
@Stateless
public class SectionClassFacade extends AbstractFacade<SectionClass> {

    @PersistenceContext(unitName = "Peanu3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SectionClassFacade() {
        super(SectionClass.class);
    }
    
}
