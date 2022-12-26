package com.universityW3.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name="order",
        schema = "universityW3")
@NoArgsConstructor
@AllArgsConstructor
public class Order {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order")
    private long id;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "course",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "id_curso")
    )
    @Column(name = "course_id")
    private int courseId;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user",
            joinColumns = @JoinColumn(name="id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_user")
    )
    @Column(name = "id_user")
    private int idUser;

    private String state;
}
