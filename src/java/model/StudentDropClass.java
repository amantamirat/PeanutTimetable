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
@Table(name = "student_drop_class")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StudentDropClass.findAll", query = "SELECT s FROM StudentDropClass s"),
    @NamedQuery(name = "StudentDropClass.findById", query = "SELECT s FROM StudentDropClass s WHERE s.id = :id")})
public class StudentDropClass implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "student", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Student student;
    @JoinColumn(name = "dropped_class", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CourseSlip droppedClass;

    public StudentDropClass() {
    }

    public StudentDropClass(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public CourseSlip getDroppedClass() {
        return droppedClass;
    }

    public void setDroppedClass(CourseSlip droppedClass) {
        this.droppedClass = droppedClass;
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
        if (!(object instanceof StudentDropClass)) {
            return false;
        }
        StudentDropClass other = (StudentDropClass) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.StudentDropClass[ id=" + id + " ]";
    }
    
}
