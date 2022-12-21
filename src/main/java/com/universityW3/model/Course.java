package com.universityW3.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "course",
        schema="universityW3")
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_curso")
    private long idCurso;

    @Column(name = "nombre", nullable = false, length = 45)
    private String nombre;

    @Column(name= "precio", nullable = false, length = 4)
    private float precio;


}
