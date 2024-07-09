package com.gmail.muhsener98.surveymanagementproject2.entity.authorization;

import com.gmail.muhsener98.surveymanagementproject2.entity.user.MyUser;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id ;

    @Column(name = "name")
    private String name ;

    @ManyToMany(mappedBy = "roles")
    private List<MyUser> users;


    public Role (){

    }

    public Role(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Role{" +
                "name='" + name + '\'' +
                '}';
    }
}
