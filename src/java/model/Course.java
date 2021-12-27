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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Amanuel
 */
@Entity
@Table(name = "course")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Course.findAll", query = "SELECT c FROM Course c"),
    @NamedQuery(name = "Course.findById", query = "SELECT c FROM Course c WHERE c.id = :id"),
    @NamedQuery(name = "Course.findByCourseCode", query = "SELECT c FROM Course c WHERE c.courseCode = :courseCode"),
    @NamedQuery(name = "Course.findByCourseTitle", query = "SELECT c FROM Course c WHERE c.courseTitle = :courseTitle"),
    @NamedQuery(name = "Course.findByLectureHours", query = "SELECT c FROM Course c WHERE c.lectureHours = :lectureHours"),
    @NamedQuery(name = "Course.findByLaboratoryHours", query = "SELECT c FROM Course c WHERE c.laboratoryHours = :laboratoryHours"),
    @NamedQuery(name = "Course.findByTutorialHours", query = "SELECT c FROM Course c WHERE c.tutorialHours = :tutorialHours")})
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "course_code")
    private String courseCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "course_title")
    private String courseTitle;
    @Basic(optional = false)
    @NotNull
    @Column(name = "lecture_hours")
    private float lectureHours;
    @Basic(optional = false)
    @NotNull
    @Column(name = "laboratory_hours")
    private float laboratoryHours;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tutorial_hours")
    private float tutorialHours;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private Collection<CourseSlip> courseSlipCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private Collection<CourseRoom> courseRoomCollection;
    @JoinColumn(name = "owner_department", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Department ownerDepartment;
    @JoinColumn(name = "department", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Department department;

    public Course() {
    }

    public Course(Integer id) {
        this.id = id;
    }

    public Course(Integer id, String courseCode, String courseTitle, float lectureHours, float laboratoryHours, float tutorialHours) {
        this.id = id;
        this.courseCode = courseCode;
        this.courseTitle = courseTitle;
        this.lectureHours = lectureHours;
        this.laboratoryHours = laboratoryHours;
        this.tutorialHours = tutorialHours;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public float getLectureHours() {
        return lectureHours;
    }

    public void setLectureHours(float lectureHours) {
        this.lectureHours = lectureHours;
    }

    public float getLaboratoryHours() {
        return laboratoryHours;
    }

    public void setLaboratoryHours(float laboratoryHours) {
        this.laboratoryHours = laboratoryHours;
    }

    public float getTutorialHours() {
        return tutorialHours;
    }

    public void setTutorialHours(float tutorialHours) {
        this.tutorialHours = tutorialHours;
    }

    @XmlTransient
    public Collection<CourseSlip> getCourseSlipCollection() {
        return courseSlipCollection;
    }

    public void setCourseSlipCollection(Collection<CourseSlip> courseSlipCollection) {
        this.courseSlipCollection = courseSlipCollection;
    }

    @XmlTransient
    public Collection<CourseRoom> getCourseRoomCollection() {
        return courseRoomCollection;
    }

    public void setCourseRoomCollection(Collection<CourseRoom> courseRoomCollection) {
        this.courseRoomCollection = courseRoomCollection;
    }

    public Department getOwnerDepartment() {
        return ownerDepartment;
    }

    public void setOwnerDepartment(Department ownerDepartment) {
        this.ownerDepartment = ownerDepartment;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
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
        if (!(object instanceof Course)) {
            return false;
        }
        Course other = (Course) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[" + courseCode + " ] "+courseTitle;
    }
    
}
