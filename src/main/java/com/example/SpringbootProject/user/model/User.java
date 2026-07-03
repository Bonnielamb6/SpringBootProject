package com.example.SpringbootProject.user.model;

import com.example.SpringbootProject.common.model.BaseEntity;
import com.example.SpringbootProject.order.model.Order;
import com.example.SpringbootProject.role.model.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "USERS")
public class User extends BaseEntity {
    @Column(nullable = false)
    @NotBlank
    private String name;
    @Column(nullable = false, unique = true)
    @NotBlank
    private String email;
    @Column(nullable = false)
    @NotBlank
    private String password;
    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();
    @OneToMany(mappedBy = "user")
    private Set<Order> orders = new HashSet<>();

    public void addRole(Role role) {
        roles.add(role);
        role.getUsers().add(this);
    }

    public void removeRole(Role role) {
        roles.remove(role);
        role.getUsers().remove(this);
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
