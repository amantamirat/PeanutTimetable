/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.AcademicCalendar;
import model.ActiveBatch;
import model.Batch;
import model.Program;

/**
 *
 * @author Amanuel
 */
@Stateless
public class AcademicCalendarFacade extends AbstractFacade<AcademicCalendar> {

    @PersistenceContext(unitName = "Peanu3PU")
    private EntityManager em;
    @EJB
    private ProgramFacade pf;
    @EJB
    private BatchFacade bf;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AcademicCalendarFacade() {
        super(AcademicCalendar.class);
    }

    /**
     * @param ac academic calendar
     * @return a List of Valid Batches that are not lies in the range between
     * the Academic calendar.
     * <p>
     * it prepares new Forward Active Batches to be created.<br>
     * After preparing of the current (Provided) Academic Calendar, the
     * Undefined Batch (relative to the current) to be active batches for this
     * Academic Calendar should be active (Not implemented though).
     * </p>
     */
    public List<Batch> prepareValidBatches(AcademicCalendar ac) {
        List<Batch> batchs = new ArrayList<>();
        int acYear = Integer.parseInt(ac.getAcademicYear().substring(0, 4));
        for (Program p : pf.findValidPrograms(ac.getSemester())) {
            for (int y = p.getMinYearOfStudy() - 1; y < p.getMaxYearOfStudy(); y++) {
                Batch b = new Batch();
                b.setProgram(p);
                String entranceYear = Integer.toString(acYear - y);
                entranceYear = entranceYear + "/" + Integer.toString(Integer.parseInt(entranceYear.substring(2, 4)) + 1);
                b.setEntranceYear(entranceYear);
                batchs.add(b);
            }
        }
        batchs.removeAll(bf.findOverlappedBatches(ac.getClassStartDate(), ac.getExamEndDate()));
        return batchs;
    }

    /**
     * @param startDate the first date
     * @param endDate the end date
     * @return a List of AcademicCalendars entities that are lies in the range
     * between the date specified.
     * <p>
     * if an entity start date is less/before than or equal to a
     * parameter/current end Date and if an entity end Date is greater/after or
     * equal to a start Date of a parameter/current.
     * <p>
     * Example : assume in the database there are AcademicCalendars registered
     * like</p>
     * AcademicCalendar1 - 20/01/2008 up to 30/04/2008 AcademicCalendars2 -
     * 20/02/2008 up to 30/05/2008 AcademicCalendar3 - 20/06/2008 up to
     * 30/09/2008
     * <p>
     * if you pass a parameter of start date as 30/02/2008 and end date
     * 10/06/2008.<br>
     * AcademicCalendars1 start date (20/01/2008) is before than the end date
     * 10/06/2008 and its end date (30/04/2008) is after than the start date
     * provided 30/02/2008 i.e. AcademicCalendars1 is in the list. the same as
     * for AcademicCalendars2 only AcademicCalendars3, since neither the start
     * date nor end date full fill the specification.
     */
    private List<AcademicCalendar> findOverlappedAcademicCalendars(Date startDate, Date endDate) {
        return (List<AcademicCalendar>) getEntityManager().createNamedQuery("AcademicCalendar.findOverlapped").setParameter("startDate", startDate).setParameter("endDate", endDate).getResultList();
    }

}
