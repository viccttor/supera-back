package br.com.banco.service.exception;

public class TransferException extends RuntimeException{

    public TransferException(String message) {
        super(message);
    }
}
