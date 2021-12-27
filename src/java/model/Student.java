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
@Table(name = "student")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Student.findAll", query = "SELECT s FROM Student s"),
    @NamedQuery(name = "Student.findById", query = "SELECT s FROM Student s WHERE s.id = :id"),
    @NamedQuery(name = "Student.findByStudentId", query = "SELECT s FROM Student s WHERE s.studentId = :studentId"),
    @NamedQuery(name = "Student.findByStudentFullName", query = "SELECT s FROM Student s WHERE s.studentFullName = :studentFullName")})
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "student_id")
    private String studentId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "student_full_name")
    private String studentFullName;
    @JoinColumn(name = "student_section", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Section studentSection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
    private Collection<StudentAddClass> studentAddClassCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
    private Collection<StudentDropClass> studentDropClassCollection;

    public Student() {
    }

    public Student(Integer id) {
        this.id = id;
    }

    public Student(Integer id, String studentId, String studentFullName) {
        this.id = id;
        this.studentId = studentId;
        this.studentFullName = studentFullName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentFullName() {
        return studentFullName;
    }

    public void setStudentFullName(String studentFullName) {
        this.studentFullName = studentFullName;
    }

    public Section getStudentSection() {
        return studentSection;
    }

    public void setStudentSection(Section studentSection) {
        this.studentSection = studentSection;
    }

    @XmlTransient
    public Collection<StudentAddClass> getStudentAddClassCollection() {
        return studentAddClassCollection;
    }

    public void setStudentAddClassCollection(Collection<StudentAddClass> studentAddClassCollection) {
        this.studentAddClassCollection = studentAddClassCollection;
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
        if (!(object instanceof Student)) {
            return false;
        }
        Student other = (Student) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Student[ id=" + id + " ]";
    }
    
}
