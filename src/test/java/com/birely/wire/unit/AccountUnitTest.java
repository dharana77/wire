package com.birely.wire.unit;

import com.birely.wire.entity.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AccountUnitTest {

  private Account account;
  private Account targetAccount;

  @BeforeEach
  void setUp() {
    account = new Account("123-456-789", "SAVINGS", 1000000L, new ArrayList<>());
    targetAccount = new Account("987-654-321", "CHECKING", 500000L, new ArrayList<>());
  }

  @Test
  void testDeposit() {
    // Given
    Long depositAmount = 500000L;

    // When
    account.deposit(depositAmount);

    // Then
    assertEquals(1500000L, account.getDeposits());
  }

  @Test
  void testWithdrawSuccess() {
    // Given
    Long withdrawAmount = 500000L;

    // When
    account.withdraw(withdrawAmount);

    // Then
    assertEquals(500000L, account.getDeposits());
    assertEquals(1500000L, account.getDailyWithdrawAmount());
  }

  @Test
  void testWithdrawFailure() {
    // Given
    Long withdrawAmount = 2000000L;

    // When & Then
    assertThrows(InvalidParameterException.class, () -> account.withdraw(withdrawAmount));
  }

  @Test
  void testTransferSuccess() {
    // Given
    Long transferAmount = 300000L;

    // When
    account.transfer(transferAmount, targetAccount);

    // Then
    assertEquals(700000L, account.getDeposits());
    assertEquals(800000L, targetAccount.getDeposits()); // 1% fee applied
    assertEquals(3300000L, account.getDailyTransferAmount());
  }

  @Test
  void testTransferFailureDueToInsufficientFunds() {
    // Given
    Long transferAmount = 2000000L;

    // When & Then
    assertThrows(InvalidParameterException.class, () -> account.transfer(transferAmount, targetAccount));
  }
}
