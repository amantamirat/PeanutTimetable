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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import model.util.RoomType;

/**
 *
 * @author Amanuel
 */
@Entity
@Table(name = "room")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Room.findAll", query = "SELECT r FROM Room r"),
    @NamedQuery(name = "Room.findById", query = "SELECT r FROM Room r WHERE r.id = :id"),
    @NamedQuery(name = "Room.findByBlockName", query = "SELECT r FROM Room r WHERE r.blockName = :blockName"),
    @NamedQuery(name = "Room.findByRoomNumber", query = "SELECT r FROM Room r WHERE r.roomNumber = :roomNumber"),
    @NamedQuery(name = "Room.findByPurpose", query = "SELECT r FROM Room r WHERE r.purpose = :purpose"),
    @NamedQuery(name = "Room.findByRoomSize", query = "SELECT r FROM Room r WHERE r.roomSize = :roomSize"),
    @NamedQuery(name = "Room.findByGround", query = "SELECT r FROM Room r WHERE r.ground = :ground")})
public class Room implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "block_name")
    private String blockName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "room_number")
    private int roomNumber;
    @Basic(optional = false)
    @NotNull
    @Column(name = "purpose")
    @Enumerated(EnumType.ORDINAL)
    private RoomType purpose;
    @Basic(optional = false)
    @NotNull
    @Column(name = "room_size")
    private int roomSize;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ground")
    private boolean ground;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "room")
    private Collection<DepartmentRoom> departmentRoomCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rroom")
    private Collection<SectionRoom> sectionRoomCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "room")
    private Collection<CourseRoom> courseRoomCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "room")
    private Collection<CollegeRoom> collegeRoomCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "room")
    private Collection<ClassSchedule> classScheduleCollection;

    public Room() {
    }

    public Room(Integer id) {
        this.id = id;
    }

    public Room(Integer id, String blockName, int roomNumber, RoomType purpose, boolean laboratory, int roomSize, boolean ground) {
        this.id = id;
        this.blockName = blockName;
        this.roomNumber = roomNumber;
        this.purpose = purpose;
        this.roomSize = roomSize;
        this.ground = ground;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public RoomType getPurpose() {
        return purpose;
    }

    public void setPurpose(RoomType purpose) {
        this.purpose = purpose;
    }

    public int getRoomSize() {
        return roomSize;
    }

    public void setRoomSize(int roomSize) {
        this.roomSize = roomSize;
    }

    public boolean getGround() {
        return ground;
    }

    public void setGround(boolean ground) {
        this.ground = ground;
    }

    @XmlTransient
    public Collection<DepartmentRoom> getDepartmentRoomCollection() {
        return departmentRoomCollection;
    }

    public void setDepartmentRoomCollection(Collection<DepartmentRoom> departmentRoomCollection) {
        this.departmentRoomCollection = departmentRoomCollection;
    }

    @XmlTransient
    public Collection<SectionRoom> getSectionRoomCollection() {
        return sectionRoomCollection;
    }

    public void setSectionRoomCollection(Collection<SectionRoom> sectionRoomCollection) {
        this.sectionRoomCollection = sectionRoomCollection;
    }

    @XmlTransient
    public Collection<CourseRoom> getCourseRoomCollection() {
        return courseRoomCollection;
    }

    public void setCourseRoomCollection(Collection<CourseRoom> courseRoomCollection) {
        this.courseRoomCollection = courseRoomCollection;
    }

    @XmlTransient
    public Collection<CollegeRoom> getCollegeRoomCollection() {
        return collegeRoomCollection;
    }

    public void setCollegeRoomCollection(Collection<CollegeRoom> collegeRoomCollection) {
        this.collegeRoomCollection = collegeRoomCollection;
    }

    @XmlTransient
    public Collection<ClassSchedule> getClassScheduleCollection() {
        return classScheduleCollection;
    }

    public void setClassScheduleCollection(Collection<ClassSchedule> classScheduleCollection) {
        this.classScheduleCollection = classScheduleCollection;
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
        if (!(object instanceof Room)) {
            return false;
        }
        Room other = (Room) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.purpose.shortTerm+" "+this.blockName+" "+this.roomNumber;
    }

}
