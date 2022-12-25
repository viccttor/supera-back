package br.com.banco.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "conta")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_conta")
    private long id;

    @Column(name = "nome_responsavel")
    private String name;


}
