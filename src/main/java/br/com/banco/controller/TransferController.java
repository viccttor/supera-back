package br.com.banco.controller;

import br.com.banco.model.dto.TransferDto;
import br.com.banco.model.Transfer;
import br.com.banco.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("transferencia")
public class TransferController {

    @Autowired
    private TransferService transferService;

    @PostMapping("new")
    private ResponseEntity<Transfer> newTrasfer(@RequestBody TransferDto transferDto) {
        return new ResponseEntity<Transfer>(transferService.newTransfer(transferDto),HttpStatus.OK);
    }

    @GetMapping("getAll")
    private ResponseEntity<List<Transfer>> getAllTransfers() {
       return new ResponseEntity<List<Transfer>>(transferService.getTransfers(), HttpStatus.OK);
    }

    @GetMapping("/getTransfers")
    private ResponseEntity<List<Transfer>> getTransfers(@RequestParam String initialDate,
                                                        @RequestParam String finalDate,
                                                        @RequestParam String name) {
        return new ResponseEntity<List<Transfer>>(transferService.getTransfers(initialDate, finalDate, name),
                HttpStatus.OK);
    }

    @GetMapping("/getTransfers/dates")
    private ResponseEntity<List<Transfer>> getTransfers(@RequestParam String initialDate,
                                                        @RequestParam String finalDate) {
        return new ResponseEntity<List<Transfer>>(transferService.getTransfers(initialDate,finalDate),
                HttpStatus.OK);
    }

    @GetMapping("/getTransfers/name")
    private ResponseEntity<List<Transfer>> getTransfers( @RequestParam String name) {
        return new ResponseEntity<List<Transfer>>(transferService.getTransfers(name),
                HttpStatus.OK);
    }

    @GetMapping("getTransfers/{id}")
    private ResponseEntity<Transfer> findById(@PathVariable Long id){
        return new ResponseEntity<Transfer>(transferService.findById(id),HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    private ResponseEntity<Transfer> deleteTransfer(@PathVariable Long id){
        return new ResponseEntity<Transfer>(transferService.deleteTransfer(id),HttpStatus.OK);
    }

}
