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
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import model.util.YearLevel;

/**
 *
 * @author Amanuel
 */
@Entity
@Table(name = "active_batch")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ActiveBatch.findAll", query = "SELECT a FROM ActiveBatch a"),
    @NamedQuery(name = "ActiveBatch.findById", query = "SELECT a FROM ActiveBatch a WHERE a.id = :id")})
public class ActiveBatch implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "activeBatch")
    private Collection<CourseSlip> courseSlipCollection;
    @JoinColumn(name = "academic_calendar", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private AcademicCalendar academicCalendar;
    @JoinColumn(name = "batch", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Batch batch;
//    @Basic(optional = true)
//    @NotNull
//    @Column(name = "relative_year_level")
//    @Enumerated(EnumType.ORDINAL)
//    private YearLevel relativeYearLevel;    
    @Enumerated(EnumType.ORDINAL)
    private transient YearLevel yearLevel;

    public ActiveBatch() {
    }

    public ActiveBatch(Integer id) {
        this.id = id;
    }

    @PostPersist
    @PostUpdate
    @PostLoad
    private void initializeYearLevel() {
        yearLevel = YearLevel.computeYearLevel(academicCalendar, batch);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @XmlTransient
    public Collection<CourseSlip> getCourseSlipCollection() {
        return courseSlipCollection;
    }

    public void setCourseSlipCollection(Collection<CourseSlip> courseSlipCollection) {
        this.courseSlipCollection = courseSlipCollection;
    }

    public AcademicCalendar getAcademicCalendar() {
        return academicCalendar;
    }

    public void setAcademicCalendar(AcademicCalendar academicCalendar) {
        this.academicCalendar = academicCalendar;
    }

    public Batch getBatch() {
        return batch;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }

//    public YearLevel getRelativeYearLevel() {
//        return relativeYearLevel;
//    }
//
//    public void setRelativeYearLevel(YearLevel relativeYearLevel) {
//        this.relativeYearLevel = relativeYearLevel;
//    }
    public YearLevel getYearLevel() {
        return yearLevel;
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
        if (!(object instanceof ActiveBatch)) {
            return false;
        }
        ActiveBatch other = (ActiveBatch) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return batch.getProgram()+ " " + yearLevel.shortTerm;
    }

}
