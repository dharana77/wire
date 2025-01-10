package com.birely.wire.service;

import com.birely.wire.dto.DeleteAccountRequestDto;
import com.birely.wire.dto.DepositRequestDto;
import com.birely.wire.dto.DepositResponseDto;
import com.birely.wire.dto.EnrollAccountRequestDto;
import com.birely.wire.dto.EnrollAccountResponseDto;
import com.birely.wire.dto.TransferRequestDto;
import com.birely.wire.dto.TransferResponseDto;
import com.birely.wire.dto.WithdrawRequestDto;
import com.birely.wire.dto.WithdrawResponseDto;
import com.birely.wire.entity.Account;
import com.birely.wire.entity.AccountHistory;
import com.birely.wire.repository.AccountHistoryRepository;
import com.birely.wire.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class AccountService {

  private final AccountRepository accountRepository;
  private final AccountHistoryRepository accountHistoryRepository;

  @Transactional
  public EnrollAccountResponseDto enrollAccount(EnrollAccountRequestDto enrollAccountRequestDto) {
    Account account = new Account(
      enrollAccountRequestDto.getAccountNumber(),
      enrollAccountRequestDto.getAccountType(),
      0L,
      new ArrayList<>());

    accountRepository.save(account);

    return EnrollAccountResponseDto.of(account);
  }

  @Transactional
  public void deleteAccount(DeleteAccountRequestDto deleteAccountRequestDto) {
    accountRepository.deleteById(deleteAccountRequestDto.getId());
  }

  @Transactional
  @Modifying
  public DepositResponseDto deposit(DepositRequestDto depositRequestDto) {
    Account account = accountRepository.findById(depositRequestDto.getAccountId()).orElseThrow();
    account.deposit(depositRequestDto.getAmount());
    return DepositResponseDto.of(account);
  }

  @Transactional
  @Modifying
  public WithdrawResponseDto withdraw(WithdrawRequestDto withdrawRequestDto) {
    Account account = accountRepository.findById(withdrawRequestDto.getAccountId()).orElseThrow();
    account.withdraw(withdrawRequestDto.getAmount());
    accountHistoryRepository.save(new AccountHistory(withdrawRequestDto.getAccountId(),
        null,
        "출금",
        withdrawRequestDto.getAmount()
        ));

    return WithdrawResponseDto.of(account);
  }

  @Transactional
  @Modifying
  public TransferResponseDto transfer(TransferRequestDto transferRequestDto) {
    Account account = accountRepository.findById(transferRequestDto.getAccountId()).orElseThrow();
    Account oppositeAccount = accountRepository.findById(transferRequestDto.getReceiptAccountId()).orElseThrow();
    account.transfer(transferRequestDto.getAmount(), oppositeAccount);

    accountHistoryRepository.save(new AccountHistory(transferRequestDto.getAccountId(),
        transferRequestDto.getReceiptAccountId(),
        "이체",
        transferRequestDto.getAmount()
      ));

    return TransferResponseDto.of(account, oppositeAccount);
  }
}
