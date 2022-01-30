/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * This class is for the User entity is has this attributes: id, password,
 * enumPrivilege, enumStatus, fullName, email and login.
 *
 * @author Alain Lozano Isasi
 */
@NamedQueries({
    //This is the query to for a user to log in with login and password.
    @NamedQuery(
            name = "findUserByLoginAndPassword", query = "SELECT u FROM User u WHERE u.login=:login and u.password=:password"
    ),
    //This is the query to find a user by their login.
    @NamedQuery(
            name = "findUserByLogin", query = "SELECT u FROM User u  WHERE u.login=:login"
    ),
    //This is the query to search for the last singIns of one user by the login
    @NamedQuery(
            name = "lastSignInsByLogin", query = "SELECT l FROM LastSignIn l WHERE l.user.login =(SELECT u.login FROM User u WHERE u.login=:login) ORDER BY l.lastSignIn ASC"
    ),
    //This is the query to change the password from a user by the login.
    @NamedQuery(
            name = "changePasswordByLogin", query = "SELECT u FROM User u  WHERE u.login=:login"
    ),
})
@Entity
@Table(name = "user", schema = "appsydb")
@Inheritance(strategy = InheritanceType.JOINED)
@XmlRootElement
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String password;
    @Enumerated(EnumType.STRING)
    private EnumPrivilege enumPrivilege;
    @Enumerated(EnumType.STRING)
    private EnumStatus enumStatus;
    private String fullName;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "login", unique = true)
    private String login;
    @OneToMany(mappedBy="user",fetch=FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<LastSignIn> lastSignIns;

    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the enumPrivilege
     */
    public EnumPrivilege getEnumPrivilege() {
        return enumPrivilege;
    }

    /**
     * @param enumPrivilege the enumPrivilege to set
     */
    public void setEnumPrivilege(EnumPrivilege enumPrivilege) {
        this.enumPrivilege = enumPrivilege;
    }

    /**
     * @return the enumStatus
     */
    public EnumStatus getEnumStatus() {
        return enumStatus;
    }

    /**
     * @param enumStatus the enumStatus to set
     */
    public void setEnumStatus(EnumStatus enumStatus) {
        this.enumStatus = enumStatus;
    }

    /**
     * @return the fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * @param fullName the fullName to set
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the lastSignins
     */
    public Set<LastSignIn> getLastSignins() {
        return lastSignIns;
    }

    /**
     * @param lastSignins the lastSignins to set
     */
    public void setLastSignins(Set<LastSignIn> lastSignIns) {
        this.lastSignIns = lastSignIns;
    }

   @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        return hash;
    }

    /**
     * 
     * @param obj
     * @return 
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        /* if (getClass() != obj.getClass()) {
        return false;
        }
        final User other = (User) obj;
        if (!Objects.equals(this.password, other.password)) {
        return false;
        }
        if (!Objects.equals(this.fullName, other.fullName)) {
        return false;
        }
        if (!Objects.equals(this.email, other.email)) {
        return false;
        }
        if (!Objects.equals(this.login, other.login)) {
        return false;
        }
        if (!Objects.equals(this.id, other.id)) {
        return false;
        }
        if (this.enumPrivilege != other.enumPrivilege) {
        return false;
        }
        if (this.enumStatus != other.enumStatus) {
        return false;
        }
        if (!Objects.equals(this.lastSignIns, other.lastSignIns)) {
        return false;
        }*/
        return true;
    }
    /**
     * 
     * @return 
     */
    @Override
    public String toString() {
        return "User{" + "id=" + id + ", password=" + password + ", enumPrivilege=" + enumPrivilege + ", enumStatus=" + enumStatus + ", fullName=" + fullName + ", email=" + email + ", login=" + login + '}';
    }

}
