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
@Table(name = "section_room")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SectionRoom.findAll", query = "SELECT s FROM SectionRoom s"),
    @NamedQuery(name = "SectionRoom.findById", query = "SELECT s FROM SectionRoom s WHERE s.id = :id")})
public class SectionRoom implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "section", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Section section;
    @JoinColumn(name = "rroom", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Room rroom;

    public SectionRoom() {
    }

    public SectionRoom(Integer id) {
        this.id = id;
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

    public Room getRroom() {
        return rroom;
    }

    public void setRroom(Room rroom) {
        this.rroom = rroom;
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
        if (!(object instanceof SectionRoom)) {
            return false;
        }
        SectionRoom other = (SectionRoom) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.SectionRoom[ id=" + id + " ]";
    }
    
}
