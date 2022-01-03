/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import model.Program;
import model.util.ProgramClassification;
import model.util.ProgramLevel;
import model.util.Semester;

/**
 *
 * @author Amanuel
 */
@Stateless
public class ProgramFacade extends AbstractFacade<Program> {

    @PersistenceContext(unitName = "Peanu3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProgramFacade() {
        super(Program.class);
    }

    @Override
    public void create(Program entity) {
        Program simillarProgram = findSimillarProgram(entity.getProgramName(), entity.getProgramLevel(), entity.getProgramClassification());
        if (simillarProgram == null || simillarProgram.isClosed()) {
            super.create(entity);
            return;
        }
        throw new EJBException("First Close The Existing Simillar Program!");
    }

    @Override
    public void edit(Program entity) {
        Program simillarProgram = findSimillarProgram(entity.getProgramName(), entity.getProgramLevel(), entity.getProgramClassification());
        if (simillarProgram == null || simillarProgram.equals(entity) || simillarProgram.isClosed()) {
             super.edit(entity);
        }
        throw new EJBException("First Close The Existing Simillar Program!");
    }

    private Program findSimillarProgram(String programName, ProgramLevel programLevel, ProgramClassification programClassification) {
        try {
            return (Program) getEntityManager().createNamedQuery("Program.findByLevelType", Program.class)
                    .setParameter("programName", programName)
                    .setParameter("programLevel", programLevel)
                    .setParameter("programClassification", programClassification).getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }

    }

    /**
     * @param semester
     * @return a List of Open Valid Programs, According to the semester valid
     * Classification.
     */
    public List<Program> findValidPrograms(Semester semester) {
        return (List<Program>) getEntityManager().createNamedQuery("Program.findValidPrograms").setParameter("programClassification", semester.getProgramClassifications()).getResultList();
    }

}
