package br.com.banco.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@Entity(name = "transferencia")
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "data_transferencia")
    private LocalDateTime dateTransference;

    @Column(name = "valor")
    private double value;

    @Column(name = "tipo")
    private String type;

    @Column(name = "nome_operador_transacao")
    private String transactionOperatorName;

//    @JoinColumn(name = "conta_id", table = "conta")
    @Column(name = "conta_id")
    private int idAccount;

}
