/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.TimePeriodConf;
import model.TimeSlot;

/**
 *
 * @author Amanuel
 */
@Stateless
public class TimePeriodConfFacade extends AbstractFacade<TimePeriodConf> {

    @PersistenceContext(unitName = "Peanu3PU")
    private EntityManager em;
    @EJB
    private TimeSlotFacade tsf;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TimePeriodConfFacade() {
        super(TimePeriodConf.class);
    }

    @Override
    public List<TimePeriodConf> findAll() {
        List<TimePeriodConf> confs = super.findAll();
        for (TimePeriodConf conf : confs) {
            getEntityManager().refresh(find(conf.getId()));
        }
        return confs;
    }

    @Override
    public void edit(TimePeriodConf entity) {
        super.edit(entity);
        for (DayOfWeek dow : TimePeriodConf.convertToSelectedDays(entity.getSelectedDays())) {
            LocalTime min = LocalTime.of(entity.getMinStartTime().getHour(), entity.getMinStartTime().getMinute());
            int period = 1;
            while (entity.getMaxEndTime().isAfter(min)) {
                TimeSlot slot = new TimeSlot();
                slot.setTimeConf(entity);
                slot.setDayOfWeek(dow);
                slot.setSlotPeriod(period++);
                slot.setStartTime(min);
                min = min.plusMinutes(entity.getSlotDuration());
                slot.setEndTime(min);
                min = min.plusMinutes(entity.getSlotInterval());
                tsf.edit(slot);
            }
        }
    }

}
