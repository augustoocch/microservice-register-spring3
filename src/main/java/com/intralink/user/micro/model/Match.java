package com.intralink.user.micro.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "matching",
        schema = "intraLink")
public class Match {

    @Id
    @Column(name = "id_usr")
    private Long idUsr;

    @Column(name = "id_mail")
    private String idMail;

    @Column(nullable = true)
    private String likes;

    @Column(nullable = true)
    private String dislikes;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id_usr")
    private Users user;


}
