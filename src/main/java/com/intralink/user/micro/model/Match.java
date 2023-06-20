package com.intralink.user.micro.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@Table(name = "match",
    schema = "intraLink")
@AllArgsConstructor
public class Match {

    @Id
    @Column(name="id_usr")
    private Long idUsr;
}
