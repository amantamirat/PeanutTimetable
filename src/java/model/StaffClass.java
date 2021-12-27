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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import model.util.StaffMode;

/**
 *
 * @author Amanuel
 */
@Entity
@Table(name = "staff_class")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StaffClass.findAll", query = "SELECT s FROM StaffClass s"),
    @NamedQuery(name = "StaffClass.findById", query = "SELECT s FROM StaffClass s WHERE s.id = :id"),
    @NamedQuery(name = "StaffClass.findByMode", query = "SELECT s FROM StaffClass s WHERE s.mode = :mode")})
public class StaffClass implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mode")
    @Enumerated(EnumType.ORDINAL)
    private StaffMode mode;
    @JoinColumn(name = "course_class", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SectionClass courseClass;
    @JoinColumn(name = "staff", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Staff staff;
    @JoinColumn(name = "lab_group", referencedColumnName = "id")
    @ManyToOne
    private SectionLabGroup labGroup;

    public StaffClass() {
    }

    public StaffClass(Integer id) {
        this.id = id;
    }

    public StaffClass(Integer id, StaffMode mode) {
        this.id = id;
        this.mode = mode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public StaffMode getMode() {
        return mode;
    }

    public void setMode(StaffMode mode) {
        this.mode = mode;
    }

    public SectionClass getCourseClass() {
        return courseClass;
    }

    public void setCourseClass(SectionClass courseClass) {
        this.courseClass = courseClass;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public SectionLabGroup getLabGroup() {
        return labGroup;
    }

    public void setLabGroup(SectionLabGroup labGroup) {
        this.labGroup = labGroup;
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
        if (!(object instanceof StaffClass)) {
            return false;
        }
        StaffClass other = (StaffClass) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.StaffClass[ id=" + id + " ]";
    }
    
}
