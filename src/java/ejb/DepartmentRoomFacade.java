/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.DepartmentRoom;

/**
 *
 * @author Amanuel
 */
@Stateless
public class DepartmentRoomFacade extends AbstractFacade<DepartmentRoom> {

    @PersistenceContext(unitName = "Peanu3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DepartmentRoomFacade() {
        super(DepartmentRoom.class);
    }
    
}
