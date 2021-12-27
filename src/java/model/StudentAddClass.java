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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Amanuel
 */
@Entity
@Table(name = "student_add_class")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StudentAddClass.findAll", query = "SELECT s FROM StudentAddClass s"),
    @NamedQuery(name = "StudentAddClass.findById", query = "SELECT s FROM StudentAddClass s WHERE s.id = :id")})
public class StudentAddClass implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "added_class", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SectionClass addedClass;
    @JoinColumn(name = "student", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Student student;

    public StudentAddClass() {
    }

    public StudentAddClass(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SectionClass getAddedClass() {
        return addedClass;
    }

    public void setAddedClass(SectionClass addedClass) {
        this.addedClass = addedClass;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
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
        if (!(object instanceof StudentAddClass)) {
            return false;
        }
        StudentAddClass other = (StudentAddClass) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.StudentAddClass[ id=" + id + " ]";
    }
    
}
