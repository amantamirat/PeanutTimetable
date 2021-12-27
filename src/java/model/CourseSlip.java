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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Amanuel
 */
@Entity
@Table(name = "course_slip")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CourseSlip.findAll", query = "SELECT c FROM CourseSlip c"),
    @NamedQuery(name = "CourseSlip.findById", query = "SELECT c FROM CourseSlip c WHERE c.id = :id"),
    @NamedQuery(name = "CourseSlip.findByBlock", query = "SELECT c FROM CourseSlip c WHERE c.block = :block"),
    @NamedQuery(name = "CourseSlip.findByEventStyle", query = "SELECT c FROM CourseSlip c WHERE c.eventStyle = :eventStyle")})
public class CourseSlip implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "block")
    private boolean block;
    @Basic(optional = false)
    @NotNull
    @Column(name = "event_Style")
    private int eventStyle;
    @JoinColumn(name = "active_batch", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ActiveBatch activeBatch;
    @JoinColumn(name = "course", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Course course;
    @OneToOne(mappedBy = "preBlock")
    private CourseSlip courseSlip;
    @JoinColumn(name = "pre_block", referencedColumnName = "id")
    @OneToOne
    private CourseSlip preBlock;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "slip")
    private Collection<SectionClass> sectionClassCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "droppedClass")
    private Collection<StudentDropClass> studentDropClassCollection;

    public CourseSlip() {
    }

    public CourseSlip(Integer id) {
        this.id = id;
    }

    public CourseSlip(Integer id, boolean block, int eventStyle) {
        this.id = id;
        this.block = block;
        this.eventStyle = eventStyle;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean getBlock() {
        return block;
    }

    public void setBlock(boolean block) {
        this.block = block;
    }

    public int getEventStyle() {
        return eventStyle;
    }

    public void setEventStyle(int eventStyle) {
        this.eventStyle = eventStyle;
    }

    public ActiveBatch getActiveBatch() {
        return activeBatch;
    }

    public void setActiveBatch(ActiveBatch activeBatch) {
        this.activeBatch = activeBatch;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public CourseSlip getCourseSlip() {
        return courseSlip;
    }

    public void setCourseSlip(CourseSlip courseSlip) {
        this.courseSlip = courseSlip;
    }

    public CourseSlip getPreBlock() {
        return preBlock;
    }

    public void setPreBlock(CourseSlip preBlock) {
        this.preBlock = preBlock;
    }

    @XmlTransient
    public Collection<SectionClass> getSectionClassCollection() {
        return sectionClassCollection;
    }

    public void setSectionClassCollection(Collection<SectionClass> sectionClassCollection) {
        this.sectionClassCollection = sectionClassCollection;
    }

    @XmlTransient
    public Collection<StudentDropClass> getStudentDropClassCollection() {
        return studentDropClassCollection;
    }

    public void setStudentDropClassCollection(Collection<StudentDropClass> studentDropClassCollection) {
        this.studentDropClassCollection = studentDropClassCollection;
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
        if (!(object instanceof CourseSlip)) {
            return false;
        }
        CourseSlip other = (CourseSlip) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.CourseSlip[ id=" + id + " ]";
    }
    
}
