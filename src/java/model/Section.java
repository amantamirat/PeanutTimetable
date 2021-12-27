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
@Table(name = "section")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Section.findAll", query = "SELECT s FROM Section s"),
    @NamedQuery(name = "Section.findById", query = "SELECT s FROM Section s WHERE s.id = :id"),
    @NamedQuery(name = "Section.findBySectionGroup", query = "SELECT s FROM Section s WHERE s.sectionGroup = :sectionGroup"),
    @NamedQuery(name = "Section.findBySectionSize", query = "SELECT s FROM Section s WHERE s.sectionSize = :sectionSize")})
public class Section implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "section_group")
    private String sectionGroup;
    @Basic(optional = false)
    @NotNull
    @Column(name = "section_size")
    private int sectionSize;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studentSection")
    private Collection<Student> studentCollection;
    @JoinColumn(name = "batch", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Batch batch;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "section")
    private Collection<SectionClass> sectionClassCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "section")
    private Collection<SectionRoom> sectionRoomCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "section")
    private Collection<SectionLabGroup> sectionLabGroupCollection;

    public Section() {
    }

    public Section(Integer id) {
        this.id = id;
    }

    public Section(Integer id, String sectionGroup, int sectionSize) {
        this.id = id;
        this.sectionGroup = sectionGroup;
        this.sectionSize = sectionSize;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSectionGroup() {
        return sectionGroup;
    }

    public void setSectionGroup(String sectionGroup) {
        this.sectionGroup = sectionGroup;
    }

    public int getSectionSize() {
        return sectionSize;
    }

    public void setSectionSize(int sectionSize) {
        this.sectionSize = sectionSize;
    }

    @XmlTransient
    public Collection<Student> getStudentCollection() {
        return studentCollection;
    }

    public void setStudentCollection(Collection<Student> studentCollection) {
        this.studentCollection = studentCollection;
    }

    public Batch getBatch() {
        return batch;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    @XmlTransient
    public Collection<SectionClass> getSectionClassCollection() {
        return sectionClassCollection;
    }

    public void setSectionClassCollection(Collection<SectionClass> sectionClassCollection) {
        this.sectionClassCollection = sectionClassCollection;
    }

    @XmlTransient
    public Collection<SectionRoom> getSectionRoomCollection() {
        return sectionRoomCollection;
    }

    public void setSectionRoomCollection(Collection<SectionRoom> sectionRoomCollection) {
        this.sectionRoomCollection = sectionRoomCollection;
    }

    public Collection<SectionLabGroup> getSectionLabGroupCollection() {
        return sectionLabGroupCollection;
    }

    public void setSectionLabGroupCollection(Collection<SectionLabGroup> sectionLabGroupCollection) {
        this.sectionLabGroupCollection = sectionLabGroupCollection;
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
        if (!(object instanceof Section)) {
            return false;
        }
        Section other = (Section) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.batch.toString()+" "+sectionGroup;
    }
    
}
