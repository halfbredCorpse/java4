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
@Table(name = "remote_users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RemoteUsers.findAll", query = "SELECT r FROM RemoteUsers r")
    , @NamedQuery(name = "RemoteUsers.findByUserId", query = "SELECT r FROM RemoteUsers r WHERE r.userId = :userId")
    , @NamedQuery(name = "RemoteUsers.findByLastName", query = "SELECT r FROM RemoteUsers r WHERE r.lastName = :lastName")
    , @NamedQuery(name = "RemoteUsers.findByFirstName", query = "SELECT r FROM RemoteUsers r WHERE r.firstName = :firstName")})
public class RemoteUsers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "User_Id")
    private String userId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "Last_Name")
    private String lastName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "First_Name")
    private String firstName;

    public RemoteUsers() {
    }

    public RemoteUsers(String userId) {
        this.userId = userId;
    }

    public RemoteUsers(String userId, String lastName, String firstName) {
        this.userId = userId;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RemoteUsers)) {
            return false;
        }
        RemoteUsers other = (RemoteUsers) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.servicedb.RemoteUsers[ userId=" + userId + " ]";
    }
    
}
