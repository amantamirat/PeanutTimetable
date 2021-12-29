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
@Table(name = "batch")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Batch.findAll", query = "SELECT b FROM Batch b"),
    @NamedQuery(name = "Batch.findById", query = "SELECT b FROM Batch b WHERE b.id = :id"),
    @NamedQuery(name = "Batch.findByEntranceYear", query = "SELECT b FROM Batch b WHERE b.entranceYear = :entranceYear"),
    @NamedQuery(name = "Batch.findByProgram_and_EntranceYear", query = "SELECT b FROM Batch b WHERE b.program.id = :programId AND b.entranceYear = :entranceYear"),
    @NamedQuery(name = "Batch.findBatchesByDates", query = "SELECT a.batch FROM ActiveBatch a WHERE a.academicCalendar.classStartDate <= :endDate AND a.academicCalendar.classStartDate >= :startDate")})
public class Batch implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 7)
    @Column(name = "entrance_year")
    private String entranceYear;
    @JoinColumn(name = "program", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Program program;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "batch")
    private Collection<ActiveBatch> activeBatchCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "batch")
    private Collection<Section> sectionCollection;

    public Batch() {
    }

    public Batch(Integer id) {
        this.id = id;
    }

    public Batch(Integer id, String entranceYear) {
        this.id = id;
        this.entranceYear = entranceYear;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEntranceYear() {
        return entranceYear;
    }

    public void setEntranceYear(String entranceYear) {
        this.entranceYear = entranceYear;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public Collection<ActiveBatch> getActiveBatchCollection() {
        return activeBatchCollection;
    }

    public void setActiveBatchCollection(Collection<ActiveBatch> activeBatchCollection) {
        this.activeBatchCollection = activeBatchCollection;
    }

    @XmlTransient
    public Collection<Section> getSectionCollection() {
        return sectionCollection;
    }

    public void setSectionCollection(Collection<Section> sectionCollection) {
        this.sectionCollection = sectionCollection;
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
        if (!(object instanceof Batch)) {
            return false;
        }
        Batch other = (Batch) object;
        if ((this.program != null && other.program != null) && (this.program.equals(other.program)
                && this.entranceYear.equals(other.entranceYear))) {
            return true;
        }
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public String getLongName() {
        return this.program.getLongName() + " " + this.entranceYear;
    }

    @Override
    public String toString() {
        return program.toString()+ " " + entranceYear +(this.id == null ? " [New]" : " ");
    }

}
