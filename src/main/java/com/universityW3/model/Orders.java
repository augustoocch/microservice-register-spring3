package com.universityW3.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name="orders",
        schema = "universityW3")
@NoArgsConstructor
@AllArgsConstructor
public class Orders {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_orders", nullable = false)
    private long id;

    @ManyToOne(targetEntity = Course.class)
    @JoinColumn(name = "curso_id", referencedColumnName = "id_curso")
    private Course courseId;
    //@Column(name = "curso_id")

    @ManyToOne(targetEntity = Users.class)
    @JoinColumn(name = "user_id", referencedColumnName = "id_user")
    private Users idUser;

    private String state;
}
