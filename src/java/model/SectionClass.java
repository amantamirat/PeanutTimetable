/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Amanuel
 */
@Entity
@Table(name = "section_class")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SectionClass.findAll", query = "SELECT s FROM SectionClass s"),
    @NamedQuery(name = "SectionClass.findById", query = "SELECT s FROM SectionClass s WHERE s.id = :id")})
public class SectionClass implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "slip", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CourseSlip slip;
    @JoinColumn(name = "section", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Section section;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "addedClass")
    private Collection<StudentAddClass> studentAddClassCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "courseClass")
    private Collection<StaffClass> staffClassCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sectionClass")
    private Collection<ClassSchedule> classScheduleCollection;

    public SectionClass() {
    }

    public SectionClass(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CourseSlip getSlip() {
        return slip;
    }

    public void setSlip(CourseSlip slip) {
        this.slip = slip;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    @XmlTransient
    public Collection<StudentAddClass> getStudentAddClassCollection() {
        return studentAddClassCollection;
    }

    public void setStudentAddClassCollection(Collection<StudentAddClass> studentAddClassCollection) {
        this.studentAddClassCollection = studentAddClassCollection;
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
        if (!(object instanceof SectionClass)) {
            return false;
        }
        SectionClass other = (SectionClass) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.SectionClass[ id=" + id + " ]";
    }
    
}
