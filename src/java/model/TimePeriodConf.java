/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import model.util.ProgramClassification;

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
    @NamedQuery(name = "TimePeriodConf.findByMaxEndTime", query = "SELECT t FROM TimePeriodConf t WHERE t.maxEndTime = :maxEndTime"),
    @NamedQuery(name = "TimePeriodConf.findByGapMinutes", query = "SELECT t FROM TimePeriodConf t WHERE t.slotInterval = :slotInterval")})
public class TimePeriodConf implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "classfication")
    @Enumerated(EnumType.ORDINAL)
    private ProgramClassification classfication;
    @Size(min = 7, max = 7)
    @Pattern(regexp = "([0-1]{7})")
    @Column(name = "selected_days")
    private String selectedDays;
    @Basic(optional = false)
    @NotNull
    @Column(name = "min_start_time", columnDefinition = "TIME")
    private LocalTime minStartTime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "max_end_time", columnDefinition = "TIME")
    private LocalTime maxEndTime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "slot_duration")
    private Integer slotDuration;
    @Basic(optional = false)
    @NotNull
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

    public ProgramClassification getClassfication() {
        return classfication;
    }

    public void setClassfication(ProgramClassification classfication) {
        this.classfication = classfication;
    }

    public String getSelectedDays() {
        return selectedDays;
    }

    public void setSelectedDays(String selectedDays) {
        this.selectedDays = selectedDays;
    }

    public LocalTime getMinStartTime() {
        return minStartTime;
    }

    public void setMinStartTime(LocalTime minStartTime) {
        this.minStartTime = minStartTime;
    }

    public LocalTime getMaxEndTime() {
        return maxEndTime;
    }

    public void setMaxEndTime(LocalTime maxEndTime) {
        this.maxEndTime = maxEndTime;
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
        return this.classfication + " [ " + id + " ]";
    }

    public static String convertToDaysFlag(List<DayOfWeek> selectedDays) {
        StringBuilder sb = new StringBuilder(7);
        for (DayOfWeek dow : DayOfWeek.values()) {
            sb.append(selectedDays.contains(dow) ? "1" : "0");
        }
        return sb.toString();
    }

    public static List<DayOfWeek> convertToSelectedDays(String selectedDays) {
        List<DayOfWeek> dayOfWeeks = new ArrayList<>();
        for (int i = 0; i < selectedDays.length(); i++) {
            if (selectedDays.charAt(i) == '1') {
                dayOfWeeks.add(DayOfWeek.of(i + 1));
            }
        }
        return dayOfWeeks;
    }

}
