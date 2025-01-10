package com.birely.wire.service;

import com.birely.wire.dto.HistoryResponseDto;
import com.birely.wire.repository.AccountHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountHistoryService {

  private final AccountHistoryRepository accountHistoryRepository;

  public List<HistoryResponseDto> getTransferAndWithdrawAccountHistories(Long accountId) {
      return accountHistoryRepository
        .findAllAccountHistoryByAccountIdInWithdrawAndTransfer(
          accountId)
        .stream().map(
          accountHistory -> new HistoryResponseDto(
            accountHistory.getType(),
            accountHistory.getTargetAccountId(),
            accountHistory.getAmount()))
        .collect(Collectors.toList());
  }
}
