package br.com.banco.service;

import br.com.banco.model.dto.AccountDto;
import br.com.banco.model.Account;
import br.com.banco.repository.AccountRepository;
import br.com.banco.service.exception.AccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    private Account account;
    public Account accountRegistration(AccountDto dto) {
        account = new Account();
        account.setName(dto.getName());
        accountRepository.save(account);
        return account;
    }

    public Account changeAccount(long id, AccountDto dto) {
       account = findById(id);

       if (account != null) {
           account.setName(dto.getName());
           accountRepository.save(account);
           return account;
       }
        return null;
    }

    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    public Account deleteAccount(long id) {
        account = findById(id);
        if (account != null) {
            accountRepository.delete(account);
            return account;
        }
        throw new AccountException("Não foi possível deletar a conta!");
    }

    public Account findById(Long id){
        account = accountRepository.findById(id).orElse(null);
        if (account != null) {
            return account;
        }
        throw new AccountException("Conta não encontrada!");
    }
}
