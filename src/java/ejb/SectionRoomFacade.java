/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.SectionRoom;

/**
 *
 * @author Amanuel
 */
@Stateless
public class SectionRoomFacade extends AbstractFacade<SectionRoom> {

    @PersistenceContext(unitName = "Peanu3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SectionRoomFacade() {
        super(SectionRoom.class);
    }
    
}
