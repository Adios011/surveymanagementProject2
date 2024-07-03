package com.gmail.muhsener98.surveymanagementproject2.entity.user;

import com.gmail.muhsener98.surveymanagementproject2.entity.authorization.Role;
import jakarta.persistence.*;
import lombok.Builder;
import org.hibernate.annotations.BatchSize;

import java.util.Date;
import java.util.List;

@Entity
@Builder(setterPrefix = "set")
@Table(name = "users")
public class MyUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "userId")
    private String userId;

    @Column(name = "first_name" , nullable = false)
    private String firstName;

    @Column(name = "last_name" , nullable = false)
    private String lastName ;

    @Column(name =  "encrypted_password" , nullable = false)
    private String encryptedPassword;

    @Column(name = "email" , nullable = false , unique = true)
    private String email;

    @Temporal(TemporalType.DATE)
    @Column(name = "birth_date" , nullable = false)
    private Date birthDate ;




    @ManyToMany
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id"))
    @BatchSize(size = 10)
    private List<Role> roles;


    public MyUser() {
    }

    public MyUser(Long id, String userId, String firstName, String lastName, String encryptedPassword, String email, Date birthDate, List<Role> roles) {
        this.id = id;
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.encryptedPassword = encryptedPassword;
        this.email = email;
        this.birthDate = birthDate;
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Role> getRoles() {
        return roles;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



}
