/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.servicedb;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jasmin Rose
 */
@Entity
@Table(name = "remote_records")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RemoteRecords.findAll", query = "SELECT r FROM RemoteRecords r")
    , @NamedQuery(name = "RemoteRecords.findByPk", query = "SELECT r FROM RemoteRecords r WHERE r.pk = :pk")
    , @NamedQuery(name = "RemoteRecords.findByUserId", query = "SELECT r FROM RemoteRecords r WHERE r.userId = :userId")
    , @NamedQuery(name = "RemoteRecords.findByDateTime", query = "SELECT r FROM RemoteRecords r WHERE r.dateTime = :dateTime")
    , @NamedQuery(name = "RemoteRecords.findByLocation", query = "SELECT r FROM RemoteRecords r WHERE r.location = :location")})
public class RemoteRecords implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK")
    private Integer pk;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "User_Id")
    private String userId;
    @Column(name = "Date_Time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;
    @Size(max = 255)
    @Column(name = "Location")
    private String location;

    public RemoteRecords() {
    }

    public RemoteRecords(Integer pk) {
        this.pk = pk;
    }

    public RemoteRecords(Integer pk, String userId) {
        this.pk = pk;
        this.userId = userId;
    }

    public Integer getPk() {
        return pk;
    }

    public void setPk(Integer pk) {
        this.pk = pk;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pk != null ? pk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RemoteRecords)) {
            return false;
        }
        RemoteRecords other = (RemoteRecords) object;
        if ((this.pk == null && other.pk != null) || (this.pk != null && !this.pk.equals(other.pk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.servicedb.RemoteRecords[ pk=" + pk + " ]";
    }
    
}
