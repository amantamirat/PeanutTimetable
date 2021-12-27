/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Collection;
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
@Table(name = "section_lab_group")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SectionLabGroup.findAll", query = "SELECT s FROM SectionLabGroup s"),
    @NamedQuery(name = "SectionLabGroup.findById", query = "SELECT s FROM SectionLabGroup s WHERE s.id = :id"),
    @NamedQuery(name = "SectionLabGroup.findBySection", query = "SELECT s FROM SectionLabGroup s WHERE s.section = :section"),
    @NamedQuery(name = "SectionLabGroup.findByLabGroup", query = "SELECT s FROM SectionLabGroup s WHERE s.labGroup = :labGroup")})
public class SectionLabGroup implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id; 
    @Basic(optional = false)
    @NotNull
    @Column(name = "lab_group")
    private int labGroup;
    @OneToMany(mappedBy = "labGroup")
    private Collection<StaffClass> staffClassCollection;
    @OneToMany(mappedBy = "labGroup")
    private Collection<ClassSchedule> classScheduleCollection;    
    @JoinColumn(name = "section", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Section section;

    public SectionLabGroup() {
    }

    public SectionLabGroup(Integer id) {
        this.id = id;
    }

    public SectionLabGroup(Integer id, Section section, int labGroup) {
        this.id = id;
        this.section = section;
        this.labGroup = labGroup;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public int getLabGroup() {
        return labGroup;
    }

    public void setLabGroup(int labGroup) {
        this.labGroup = labGroup;
    }

    @XmlTransient
    public Collection<StaffClass> getStaffClassCollection() {
        return staffClassCollection;
    }

    public void setStaffClassCollection(Collection<StaffClass> staffClassCollection) {
        this.staffClassCollection = staffClassCollection;
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
        if (!(object instanceof SectionLabGroup)) {
            return false;
        }
        SectionLabGroup other = (SectionLabGroup) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.SectionLabGroup[ id=" + id + " ]";
    }
    
}
