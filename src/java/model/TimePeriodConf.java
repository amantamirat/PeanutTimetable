/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Amanuel
 */
@Entity
@Table(name = "time_period_conf")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TimePeriodConf.findAll", query = "SELECT t FROM TimePeriodConf t"),
    @NamedQuery(name = "TimePeriodConf.findById", query = "SELECT t FROM TimePeriodConf t WHERE t.id = :id"),
    @NamedQuery(name = "TimePeriodConf.findBySelectedDays", query = "SELECT t FROM TimePeriodConf t WHERE t.selectedDays = :selectedDays"),
    @NamedQuery(name = "TimePeriodConf.findByMinStartTime", query = "SELECT t FROM TimePeriodConf t WHERE t.minStartTime = :minStartTime"),
    @NamedQuery(name = "TimePeriodConf.findByMaxStartTime", query = "SELECT t FROM TimePeriodConf t WHERE t.maxStartTime = :maxStartTime"),
    @NamedQuery(name = "TimePeriodConf.findByGapMinutes", query = "SELECT t FROM TimePeriodConf t WHERE t.slotInterval = :slotInterval")})
public class TimePeriodConf implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 7)
    @Column(name = "selected_days")
    private String selectedDays;
    @Column(name = "min_start_time")
    @Temporal(TemporalType.TIME)
    private Date minStartTime;
    @Column(name = "max_start_time")
    @Temporal(TemporalType.TIME)
    private Date maxStartTime;
    @Column(name = "slot_duration")
    private Integer slotDuration;
    @Column(name = "slot_interval")
    private Integer slotInterval;
    @Column(name = "time_zone")
    private Integer timeZone;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "timeConf")
    private Collection<CalendarPeriod> calendarPeriodCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "timeConf")
    private Collection<TimeSlot> timeSlotCollection;

    public TimePeriodConf() {
    }

    public TimePeriodConf(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSelectedDays() {
        return selectedDays;
    }

    public void setSelectedDays(String selectedDays) {
        this.selectedDays = selectedDays;
    }

    public Date getMinStartTime() {
        return minStartTime;
    }

    public void setMinStartTime(Date minStartTime) {
        this.minStartTime = minStartTime;
    }

    public Date getMaxStartTime() {
        return maxStartTime;
    }

    public void setMaxStartTime(Date maxStartTime) {
        this.maxStartTime = maxStartTime;
    }

    public Integer getSlotDuration() {
        return slotDuration;
    }

    public void setSlotDuration(Integer slotDuration) {
        this.slotDuration = slotDuration;
    }

    public Integer getSlotInterval() {
        return slotInterval;
    }

    public void setSlotInterval(Integer slotInterval) {
        this.slotInterval = slotInterval;
    }    

    public Integer getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(Integer timeZone) {
        this.timeZone = timeZone;
    }
    

    @XmlTransient
    public Collection<CalendarPeriod> getCalendarPeriodCollection() {
        return calendarPeriodCollection;
    }

    public void setCalendarPeriodCollection(Collection<CalendarPeriod> calendarPeriodCollection) {
        this.calendarPeriodCollection = calendarPeriodCollection;
    }

    @XmlTransient
    public Collection<TimeSlot> getTimeSlotCollection() {
        return timeSlotCollection;
    }

    public void setTimeSlotCollection(Collection<TimeSlot> timeSlotCollection) {
        this.timeSlotCollection = timeSlotCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TimePeriodConf)) {
            return false;
        }
        TimePeriodConf other = (TimePeriodConf) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.TimePeriodConf[ id=" + id + " ]";
    }
    
}