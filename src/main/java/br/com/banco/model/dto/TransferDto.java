package br.com.banco.model.dto;

import lombok.Data;


@Data
public class TransferDto  {

    private final double value;
    private final String type;
    private final String transactionOperatorName;
    private final int idAccount;
}