package com.birely.wire.integration;

import com.birely.wire.entity.Account;
import com.birely.wire.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class AccountIntegrationTest {
  @Autowired
  private AccountRepository accountRepository;

  @Test
  void testAccountCreationAndPersistence() {
    // Given
    Account account = new Account("123-456-789", "SAVINGS", 1000000L, new ArrayList<>());

    // When
    Account savedAccount = accountRepository.save(account);

    // Then
    assertNotNull(savedAccount.getId());
    assertEquals("123-456-789", savedAccount.getAccountNumber());
    assertEquals(1000000L, savedAccount.getDeposits());
  }

  @Test
  void testDepositPersistence() {
    // Given
    Account account = new Account("123-456-789", "SAVINGS", 1000000L, new ArrayList<>());
    accountRepository.save(account);

    // When
    account.deposit(500000L);
    accountRepository.save(account);

    // Then
    Account fetchedAccount = accountRepository.findById(account.getId()).orElseThrow();
    assertEquals(1500000L, fetchedAccount.getDeposits());
  }

  @Test
  void testWithdrawPersistence() {
    // Given
    Account account = new Account("123-456-789", "SAVINGS", 1000000L, new ArrayList<>());
    accountRepository.save(account);

    // When
    account.withdraw(500000L);
    accountRepository.save(account);

    // Then
    Account fetchedAccount = accountRepository.findById(account.getId()).orElseThrow();
    assertEquals(500000L, fetchedAccount.getDeposits());
  }

  @Test
  void testTransferPersistence() {
    // Given
    Account account = new Account("123-456-789", "SAVINGS", 1000000L, new ArrayList<>());
    Account targetAccount = new Account("987-654-321", "CHECKING", 500000L, new ArrayList<>());
    accountRepository.save(account);
    accountRepository.save(targetAccount);

    // When
    account.transfer(300000L, targetAccount);
    accountRepository.save(account);
    accountRepository.save(targetAccount);

    // Then
    Account fetchedAccount = accountRepository.findById(account.getId()).orElseThrow();
    Account fetchedTargetAccount = accountRepository.findById(targetAccount.getId()).orElseThrow();

    assertEquals(700000L, fetchedAccount.getDeposits());
    assertEquals(800000L, fetchedTargetAccount.getDeposits());
  }
}
