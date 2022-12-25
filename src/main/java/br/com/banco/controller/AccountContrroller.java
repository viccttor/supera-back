package br.com.banco.controller;

import br.com.banco.model.dto.AccountDto;
import br.com.banco.model.Account;
import br.com.banco.repository.AccountRepository;
import br.com.banco.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("conta")
public class AccountContrroller {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository  accountRepository;

    @PostMapping("new")
    private ResponseEntity<Account> accountRegistration(@RequestBody AccountDto dto) {
        return new ResponseEntity<Account>(accountService.accountRegistration(dto),HttpStatus.CREATED);
    }

    @PutMapping("update/{id}")
    private ResponseEntity<Account>  changeAccount(@PathVariable long id, @RequestBody AccountDto dto) {
        return new ResponseEntity<Account>(accountService.changeAccount(id, dto),HttpStatus.OK);
    }

    @GetMapping("getAll")
    private ResponseEntity<List<Account>> getAccounts() {
        return new ResponseEntity<List<Account>>(accountService.getAccounts(), HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    private ResponseEntity<Account> deleteAccount(@PathVariable Long id){
        return new ResponseEntity<Account>(accountService.deleteAccount(id), HttpStatus.OK);
    }

    @GetMapping("getAccount/{id}")
    private ResponseEntity<Account> findById(@PathVariable Long id){
        return new ResponseEntity<Account>(accountService.findById(id),HttpStatus.OK);
    }

}
