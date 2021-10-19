package com.auth.exam.Models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String role;

    @ManyToMany(mappedBy = "roles")
    private List<User> users=new ArrayList<>();

    public Role() {
    }

    public Role(String role, List<User> users) {
        this.role = role;
        this.users = users;
    }

    public long getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
