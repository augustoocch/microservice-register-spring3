package com.universityW3.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name="admin",
        schema="universityW3")
@NoArgsConstructor
@AllArgsConstructor
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_admin")
    private Long id;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Column(name = "surname", nullable = false, length = 45)
    private String surname;

    @Column(name = "password", nullable = false, length = 45)
    private String password;

    @Column(name = "mat", nullable = false)
    private String mat;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="admin_roles",
            joinColumns = @JoinColumn(name="id_admin"),
            inverseJoinColumns = @JoinColumn(name="id_role")
    )
    private Set<Roles> roles;

}
