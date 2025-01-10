package com.birely.wire.unit;

import com.birely.wire.dto.HistoryResponseDto;
import com.birely.wire.entity.AccountHistory;
import com.birely.wire.repository.AccountHistoryRepository;
import com.birely.wire.service.AccountHistoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountHistoryServiceTest {

  @Mock
  private AccountHistoryRepository accountHistoryRepository;

  @InjectMocks
  private AccountHistoryService accountHistoryService;

  @Test
  void testFindByTypeIsTransferOrWithdrawal() {
    // Given
    List<AccountHistory> mockHistories = List.of(
      createMockHistory(1L, 2L, "이체", 1000L),
      createMockHistory(1L,null, "출금", 500L)
    );
    when(accountHistoryRepository.findAllAccountHistoryByAccountIdInWithdrawAndTransfer(1L))
      .thenReturn(mockHistories);

    // When
    List<HistoryResponseDto> results = accountHistoryService.getTransferAndWithdrawAccountHistories(1L);

    // Then
    assertEquals(2, results.size());
    assertTrue(results.stream()
      .allMatch(h -> h.getType().equals("이체") || h.getType().equals("출금")));
  }

  private AccountHistory createMockHistory(Long accountId, Long targetAccountId, String type, Long amount) {
    AccountHistory history = new AccountHistory(accountId, targetAccountId, type, amount);
    return history;
  }

}
