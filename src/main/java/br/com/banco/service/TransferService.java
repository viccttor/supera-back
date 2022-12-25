package br.com.banco.service;

import br.com.banco.model.dto.TransferDto;
import br.com.banco.model.Transfer;
import br.com.banco.repository.TransferRepository;

import br.com.banco.service.exception.TransferException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TransferService {

    @Autowired
    private TransferRepository transferRepository;

    private Transfer transfer;
    public Transfer newTransfer(TransferDto transferDto) {
        transfer = new Transfer();
        transfer.setDateTransference(LocalDateTime.now());
        transfer.setValue(transferDto.getValue());
        transfer.setType(transferDto.getType());
        transfer.setTransactionOperatorName(transferDto.getTransactionOperatorName());
        transfer.setIdAccount(transferDto.getIdAccount());
        transferRepository.save(transfer);

        return transfer;
    }

    public List<Transfer> getTransfers() {
        return transferRepository.findAll();
    }


    public List<Transfer> getTransfers(String initialDate, String finalDate) {

        List<Transfer> allTransfers = getTransfers();
        List<Transfer> filterTransfers = new ArrayList<>();

        LocalDate ldInitial = LocalDate.parse(initialDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate ldFinal = LocalDate.parse(finalDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        LocalTime ltInitial = LocalTime.parse("00:00:00");
        LocalTime ltFinal = LocalTime.parse("23:59:59");

        LocalDateTime initialDt = LocalDateTime.of(ldInitial,ltInitial);
        LocalDateTime finalDt = LocalDateTime.of(ldFinal,ltFinal);

        allTransfers.stream()
                .filter(t-> t.getDateTransference().isAfter(initialDt))
                .filter(t-> t.getDateTransference().isBefore(finalDt))
                .forEach(filterTransfers::add);

        return  filterTransfers;
    }


    public List<Transfer> getTransfers(String initialDate, String finalDate, String name) {

        List<Transfer> allTransfers = getTransfers();
        List<Transfer> filterTransfers = new ArrayList<>();

        LocalDate ldInitial = LocalDate.parse(initialDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate ldFinal = LocalDate.parse(finalDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        LocalTime ltInitial = LocalTime.parse("00:00:00");
        LocalTime ltFinal = LocalTime.parse("23:59:59");

        LocalDateTime initialDt = LocalDateTime.of(ldInitial,ltInitial);
        LocalDateTime finalDt = LocalDateTime.of(ldFinal,ltFinal);

        allTransfers.stream()
                .filter(t-> t.getDateTransference().isAfter(initialDt))
                .filter(t-> t.getDateTransference().isBefore(finalDt))
                .filter(t-> t.getTransactionOperatorName() != null
                        && t.getTransactionOperatorName().equalsIgnoreCase(name))
                .forEach(filterTransfers::add);

        return  filterTransfers;
    }


    public List<Transfer> getTransfers(String name) {

        List<Transfer> allTransfers = getTransfers();
        List<Transfer> filterTransfers = new ArrayList<>();

        allTransfers.stream()
                .filter(t-> t.getTransactionOperatorName() != null
                        && t.getTransactionOperatorName().equalsIgnoreCase(name))
                .forEach(filterTransfers::add);

        return  filterTransfers;
    }

    public Transfer findById(Long id){
        transfer = transferRepository.findById(id).orElse(null);
        if (transfer != null){
            return transfer;
        }
        throw new TransferException("Transferência não encontrada!");
    }


    public Transfer deleteTransfer(Long id){
        transfer = findById(id);
        if (transfer != null){
            transferRepository.delete(transfer);
            return transfer;
        }
        throw new TransferException("Transferência não encontrada!");
    }

    private Double[] amountsAndBalanceOfTransfers(List<Transfer> filterTransfersByIdAccount){
        double balance = 0.0;
        double totalValues = 0.0;
        Double[] balanceAndTotalValues = new Double[2];

        for (Transfer t : filterTransfersByIdAccount) {
            balance += t.getValue();
            if (t.getValue() >= 0){
                totalValues += t.getValue();
            } else {
                totalValues += t.getValue() * -1;
            }
        }

        balanceAndTotalValues[0] = balance;
        balanceAndTotalValues[1] = totalValues;

        return balanceAndTotalValues;
    }
}
