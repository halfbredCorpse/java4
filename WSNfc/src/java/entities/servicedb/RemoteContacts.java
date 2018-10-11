/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.servicedb;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jasmin Rose
 */
@Entity
@Table(name = "remote_contacts")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RemoteContacts.findAll", query = "SELECT r FROM RemoteContacts r")
    , @NamedQuery(name = "RemoteContacts.findByPk", query = "SELECT r FROM RemoteContacts r WHERE r.pk = :pk")
    , @NamedQuery(name = "RemoteContacts.findByUserId", query = "SELECT r FROM RemoteContacts r WHERE r.userId = :userId")
    , @NamedQuery(name = "RemoteContacts.findByEmergencyContact", query = "SELECT r FROM RemoteContacts r WHERE r.emergencyContact = :emergencyContact")})
public class RemoteContacts implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PK")
    private Integer pk;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "User_Id")
    private String userId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "Emergency_Contact")
    private String emergencyContact;

    public RemoteContacts() {
    }

    public RemoteContacts(Integer pk) {
        this.pk = pk;
    }

    public RemoteContacts(Integer pk, String userId, String emergencyContact) {
        this.pk = pk;
        this.userId = userId;
        this.emergencyContact = emergencyContact;
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

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
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
        if (!(object instanceof RemoteContacts)) {
            return false;
        }
        RemoteContacts other = (RemoteContacts) object;
        if ((this.pk == null && other.pk != null) || (this.pk != null && !this.pk.equals(other.pk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.servicedb.RemoteContacts[ pk=" + pk + " ]";
    }
    
}
