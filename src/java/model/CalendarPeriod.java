/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Amanuel
 */
@Entity
@Table(name = "calendar_period")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CalendarPeriod.findAll", query = "SELECT c FROM CalendarPeriod c"),
    @NamedQuery(name = "CalendarPeriod.findById", query = "SELECT c FROM CalendarPeriod c WHERE c.id = :id"),
    @NamedQuery(name = "CalendarPeriod.findByClassfication", query = "SELECT c FROM CalendarPeriod c WHERE c.classfication = :classfication")})
public class CalendarPeriod implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Column(name = "classfication")
    private Integer classfication;
    @JoinColumn(name = "academic_calendar", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private AcademicCalendar academicCalendar;
    @JoinColumn(name = "time_conf", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TimePeriodConf timeConf;

    public CalendarPeriod() {
    }

    public CalendarPeriod(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClassfication() {
        return classfication;
    }

    public void setClassfication(Integer classfication) {
        this.classfication = classfication;
    }

    public AcademicCalendar getAcademicCalendar() {
        return academicCalendar;
    }

    public void setAcademicCalendar(AcademicCalendar academicCalendar) {
        this.academicCalendar = academicCalendar;
    }

    public TimePeriodConf getTimeConf() {
        return timeConf;
    }

    public void setTimeConf(TimePeriodConf timeConf) {
        this.timeConf = timeConf;
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
        if (!(object instanceof CalendarPeriod)) {
            return false;
        }
        CalendarPeriod other = (CalendarPeriod) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.CalendarPeriod[ id=" + id + " ]";
    }
    
}
