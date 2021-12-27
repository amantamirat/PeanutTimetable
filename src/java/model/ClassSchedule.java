/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Amanuel
 */
@Entity
@Table(name = "class_schedule")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClassSchedule.findAll", query = "SELECT c FROM ClassSchedule c"),
    @NamedQuery(name = "ClassSchedule.findById", query = "SELECT c FROM ClassSchedule c WHERE c.id = :id"),
    @NamedQuery(name = "ClassSchedule.findByDayOfWeek", query = "SELECT c FROM ClassSchedule c WHERE c.dayOfWeek = :dayOfWeek"),
    @NamedQuery(name = "ClassSchedule.findByStartTime", query = "SELECT c FROM ClassSchedule c WHERE c.startTime = :startTime"),
    @NamedQuery(name = "ClassSchedule.findByEndTime", query = "SELECT c FROM ClassSchedule c WHERE c.endTime = :endTime"),
    @NamedQuery(name = "ClassSchedule.findByRemark", query = "SELECT c FROM ClassSchedule c WHERE c.remark = :remark"),
    @NamedQuery(name = "ClassSchedule.findByOldLabGroup", query = "SELECT c FROM ClassSchedule c WHERE c.oldLabGroup = :oldLabGroup")})
public class ClassSchedule implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "day_of_week")
    private int dayOfWeek;
    @Basic(optional = false)
    @NotNull
    @Column(name = "start_time")
    @Temporal(TemporalType.TIME)
    private Date startTime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "end_time")
    @Temporal(TemporalType.TIME)
    private Date endTime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "remark")
    private int remark;
    @Basic(optional = false)
    @NotNull
    @Column(name = "old_lab_group")
    private int oldLabGroup;
    @JoinColumn(name = "section_class", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SectionClass sectionClass;
    @JoinColumn(name = "room", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Room room;
    @JoinColumn(name = "lab_group", referencedColumnName = "id")
    @ManyToOne
    private SectionLabGroup labGroup;
    @JoinColumn(name = "time_slot", referencedColumnName = "id")
    @ManyToOne
    private TimeSlot timeSlot;

    public ClassSchedule() {
    }

    public ClassSchedule(Integer id) {
        this.id = id;
    }

    public ClassSchedule(Integer id, int dayOfWeek, Date startTime, Date endTime, int remark, int oldLabGroup) {
        this.id = id;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.remark = remark;
        this.oldLabGroup = oldLabGroup;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getRemark() {
        return remark;
    }

    public void setRemark(int remark) {
        this.remark = remark;
    }

    public int getOldLabGroup() {
        return oldLabGroup;
    }

    public void setOldLabGroup(int oldLabGroup) {
        this.oldLabGroup = oldLabGroup;
    }

    public SectionClass getSectionClass() {
        return sectionClass;
    }

    public void setSectionClass(SectionClass sectionClass) {
        this.sectionClass = sectionClass;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public SectionLabGroup getLabGroup() {
        return labGroup;
    }

    public void setLabGroup(SectionLabGroup labGroup) {
        this.labGroup = labGroup;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
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
        if (!(object instanceof ClassSchedule)) {
            return false;
        }
        ClassSchedule other = (ClassSchedule) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.ClassSchedule[ id=" + id + " ]";
    }
    
}
