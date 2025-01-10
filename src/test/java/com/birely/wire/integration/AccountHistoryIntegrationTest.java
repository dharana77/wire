package com.birely.wire.integration;

import com.birely.wire.entity.AccountHistory;
import com.birely.wire.repository.AccountHistoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AccountHistoryIntegrationTest {

  @Autowired
  private AccountHistoryRepository accountHistoryRepository;

  @Test
  void testFindByTypeIsTransferOrWithdrawal() {
    // Given
    accountHistoryRepository.save(createAccountHistory(1L, null, "입금", 2000L));
    accountHistoryRepository.save(createAccountHistory(1L, 2L,"이체", 1000L));
    accountHistoryRepository.save(createAccountHistory(1L, null, "출금", 500L));

    // When
    List<AccountHistory> results = accountHistoryRepository.findAllAccountHistoryByAccountIdInWithdrawAndTransfer(1L);

    // Then
    assertEquals(2, results.size());
    assertTrue(results.stream()
      .allMatch(h -> h.getType().equals("이체") || h.getType().equals("출금")));
  }

  // Helper method to create AccountHistory data
  private AccountHistory createAccountHistory(Long accountId, Long targetAccountId, String type, Long amount) {
    AccountHistory history = new AccountHistory(accountId, targetAccountId, type, amount);
    return history;
  }
}
