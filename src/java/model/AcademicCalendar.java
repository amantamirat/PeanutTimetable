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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import model.util.Semester;

/**
 *
 * @author Amanuel
 */
@Entity
@Table(name = "academic_calendar")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AcademicCalendar.findAll", query = "SELECT a FROM AcademicCalendar a"),
    @NamedQuery(name = "AcademicCalendar.findById", query = "SELECT a FROM AcademicCalendar a WHERE a.id = :id"),
    @NamedQuery(name = "AcademicCalendar.findByAcademicYear", query = "SELECT a FROM AcademicCalendar a WHERE a.academicYear = :academicYear"),
    @NamedQuery(name = "AcademicCalendar.findBySemester", query = "SELECT a FROM AcademicCalendar a WHERE a.semester = :semester"),
    @NamedQuery(name = "AcademicCalendar.findByClassStartDate", query = "SELECT a FROM AcademicCalendar a WHERE a.classStartDate = :classStartDate"),
    @NamedQuery(name = "AcademicCalendar.findByClassEndDate", query = "SELECT a FROM AcademicCalendar a WHERE a.classEndDate = :classEndDate"),
    @NamedQuery(name = "AcademicCalendar.findByExamStartDate", query = "SELECT a FROM AcademicCalendar a WHERE a.examStartDate = :examStartDate"),
    @NamedQuery(name = "AcademicCalendar.findByExamEndDate", query = "SELECT a FROM AcademicCalendar a WHERE a.examEndDate = :examEndDate"),
    @NamedQuery(name = "AcademicCalendar.findOverlapped", query = "SELECT a FROM AcademicCalendar a WHERE a.classStartDate <= :endDate AND a.classStartDate >= :startDate"),})
public class AcademicCalendar implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 7)
    @Column(name = "academic_year")
    private String academicYear;
    @Basic(optional = false)
    @NotNull
    @Column(name = "semester")
    @Enumerated(EnumType.ORDINAL)
    private Semester semester;
    @Basic(optional = false)
    @NotNull
    @Column(name = "class_start_date")
    @Temporal(TemporalType.DATE)
    private Date classStartDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "class_end_date")
    @Temporal(TemporalType.DATE)
    private Date classEndDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "exam_start_date")
    @Temporal(TemporalType.DATE)
    private Date examStartDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "exam_end_date")
    @Temporal(TemporalType.DATE)
    private Date examEndDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "academicCalendar")
    private Collection<ActiveBatch> activeBatchCollection;
    

    public AcademicCalendar() {
    }

    public AcademicCalendar(Integer id) {
        this.id = id;
    }

    public AcademicCalendar(Integer id, String academicYear, Semester semester, Date classStartDate, Date classEndDate, Date examStartDate, Date examEndDate) {
        this.id = id;
        this.academicYear = academicYear;
        this.semester = semester;
        this.classStartDate = classStartDate;
        this.classEndDate = classEndDate;
        this.examStartDate = examStartDate;
        this.examEndDate = examEndDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public Date getClassStartDate() {
        return classStartDate;
    }

    public void setClassStartDate(Date classStartDate) {
        this.classStartDate = classStartDate;
    }

    public Date getClassEndDate() {
        return classEndDate;
    }

    public void setClassEndDate(Date classEndDate) {
        this.classEndDate = classEndDate;
    }

    public Date getExamStartDate() {
        return examStartDate;
    }

    public void setExamStartDate(Date examStartDate) {
        this.examStartDate = examStartDate;
    }

    public Date getExamEndDate() {
        return examEndDate;
    }

    public void setExamEndDate(Date examEndDate) {
        this.examEndDate = examEndDate;
    }    
    

    @XmlTransient
    public Collection<ActiveBatch> getActiveBatchCollection() {
        return activeBatchCollection;
    }

    public void setActiveBatchCollection(Collection<ActiveBatch> activeBatchCollection) {
        this.activeBatchCollection = activeBatchCollection;
    }

    public boolean isValidYear() {
        try {
            int acf = Integer.parseInt(academicYear.substring(2, 4));
            int acb = Integer.parseInt(academicYear.substring(5, 7));
            return acb - acf == 1;
        } catch (NullPointerException | NumberFormatException | ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    public boolean checkDates() {
        try {
            return !(!classEndDate.after(classStartDate)
                    || !examStartDate.after(classEndDate)
                    || !examEndDate.after(examStartDate));
        } catch (NullPointerException e) {
            return false;
        }
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
        if (!(object instanceof AcademicCalendar)) {
            return false;
        }
        AcademicCalendar other = (AcademicCalendar) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.academicYear + " " + this.semester.getShortTerm();
    }

}
