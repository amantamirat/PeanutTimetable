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
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import model.util.ProgramClassification;
import model.util.ProgramLevel;

/**
 *
 * @author Amanuel
 */
@Entity
@Table(name = "program")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Program.findAll", query = "SELECT p FROM Program p"),
    @NamedQuery(name = "Program.findById", query = "SELECT p FROM Program p WHERE p.id = :id"),
    @NamedQuery(name = "Program.findByProgramName", query = "SELECT p FROM Program p WHERE p.programName = :programName"),
    @NamedQuery(name = "Program.findByProgramLevel", query = "SELECT p FROM Program p WHERE p.programLevel = :programLevel"),
    @NamedQuery(name = "Program.findByLevelType", query = "SELECT p FROM Program p WHERE p.programName = :programName AND p.programLevel = :programLevel AND p.programClassification = :programClassification"),
    @NamedQuery(name = "Program.findValidPrograms", query = "SELECT p FROM Program p WHERE p.closed = FALSE AND p.programClassification in :programClassification"),
    @NamedQuery(name = "Program.findByMaxYearOfStudy", query = "SELECT p FROM Program p WHERE p.maxYearOfStudy = :maxYearOfStudy")})
public class Program implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "program_name")
    private String programName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "program_level")
    @Enumerated(EnumType.ORDINAL)
    private ProgramLevel programLevel;
    @Basic(optional = false)
    @NotNull
    @Column(name = "program_classification")
    @Enumerated(EnumType.ORDINAL)
    private ProgramClassification programClassification;
    @Basic(optional = false)
    @NotNull
    @Min(value = 1)
    @Max(value = 6)
    @Column(name = "min_year_of_study")
    private int minYearOfStudy;
    @Basic(optional = false)
    @NotNull
    @Min(value = 1)
    @Max(value = 7)
    @Column(name = "max_year_of_study")
    private int maxYearOfStudy;
    @Basic(optional = false)
    @NotNull
    @Column(name = "closed")
    private boolean closed;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "program")
    private Collection<Batch> batchCollection;
    @JoinColumn(name = "department", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Department department;

    public Program() {
    }

    public Program(Integer id) {
        this.id = id;
    }

    public Program(Integer id, String programName, ProgramLevel programLevel, int maxYearOfStudy) {
        this.id = id;
        this.programName = programName;
        this.programLevel = programLevel;
        this.maxYearOfStudy = maxYearOfStudy;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public ProgramLevel getProgramLevel() {
        return programLevel;
    }

    public void setProgramLevel(ProgramLevel programLevel) {
        this.programLevel = programLevel;
    }

    public ProgramClassification getProgramClassification() {
        return programClassification;
    }

    public void setProgramClassification(ProgramClassification programClassification) {
        this.programClassification = programClassification;
    }   

    public int getMinYearOfStudy() {
        return minYearOfStudy;
    }

    public void setMinYearOfStudy(int minYearOfStudy) {
        this.minYearOfStudy = minYearOfStudy;
    }    

    public int getMaxYearOfStudy() {
        return maxYearOfStudy;
    }

    public void setMaxYearOfStudy(int maxYearOfStudy) {
        this.maxYearOfStudy = maxYearOfStudy;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    } 
    

    @XmlTransient
    public Collection<Batch> getBatchCollection() {
        return batchCollection;
    }

    public void setBatchCollection(Collection<Batch> batchCollection) {
        this.batchCollection = batchCollection;
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
        if (!(object instanceof Program)) {
            return false;
        }
        Program other = (Program) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
    public String getLongName() {
        return this.department.getLongName()+" "+this.toString();
    }

    @Override
    public String toString() {
        return this.programName+" "+this.programLevel.shortTerm+" "+this.programClassification.shortTerm;
    }
    
}
