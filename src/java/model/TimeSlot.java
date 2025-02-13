/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Amanuel
 */
@Entity
@Table(name = "time_slot")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TimeSlot.findAll", query = "SELECT t FROM TimeSlot t"),
    @NamedQuery(name = "TimeSlot.findById", query = "SELECT t FROM TimeSlot t WHERE t.id = :id"),
    @NamedQuery(name = "TimeSlot.findByDayOfWeek", query = "SELECT t FROM TimeSlot t WHERE t.dayOfWeek = :dayOfWeek"),
    @NamedQuery(name = "TimeSlot.findByPeriod", query = "SELECT t FROM TimeSlot t WHERE t.slotPeriod = :slotPeriod"),
    @NamedQuery(name = "TimeSlot.findByStartTime", query = "SELECT t FROM TimeSlot t WHERE t.startTime = :startTime"),
    @NamedQuery(name = "TimeSlot.findByEndTime", query = "SELECT t FROM TimeSlot t WHERE t.endTime = :endTime")})
public class TimeSlot implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "day_of_week")
    @Enumerated(EnumType.ORDINAL)
    private DayOfWeek dayOfWeek;
    @Basic(optional = false)
    @NotNull
    @Column(name = "slot_period")
    private Integer slotPeriod;
    @Basic(optional = false)
    @NotNull
    @Column(name = "start_time", columnDefinition = "TIME")
    private LocalTime startTime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "end_time", columnDefinition = "TIME")
    private LocalTime endTime;
    @JoinColumn(name = "time_conf", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TimePeriodConf timeConf;
    @OneToMany(mappedBy = "timeSlot")
    private Collection<ClassSchedule> classScheduleCollection;

    public TimeSlot() {
    }

    public TimeSlot(Integer id) {
        this.id = id;
    }

    public TimeSlot(DayOfWeek dayOfWeek, Integer slotPeriod, TimePeriodConf timeConf) {
        this.dayOfWeek = dayOfWeek;
        this.slotPeriod = slotPeriod;
        this.timeConf = timeConf;
    }  
    

    public TimeSlot(Integer id, DayOfWeek dayOfWeek, int slotPeriod, LocalTime startTime, LocalTime endTime) {
        this.id = id;
        this.dayOfWeek = dayOfWeek;
        this.slotPeriod = slotPeriod;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Integer getSlotPeriod() {
        return slotPeriod;
    }

    public void setSlotPeriod(Integer slotPeriod) {
        this.slotPeriod = slotPeriod;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public TimePeriodConf getTimeConf() {
        return timeConf;
    }

    public void setTimeConf(TimePeriodConf timePeriodConf) {
        this.timeConf = timePeriodConf;
    }

    @XmlTransient
    public Collection<ClassSchedule> getClassScheduleCollection() {
        return classScheduleCollection;
    }

    public void setClassScheduleCollection(Collection<ClassSchedule> classScheduleCollection) {
        this.classScheduleCollection = classScheduleCollection;
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
        if (!(object instanceof TimeSlot)) {
            return false;
        }
        TimeSlot other = (TimeSlot) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.TimeSlot[ id=" + id + " ]";
    }

}
