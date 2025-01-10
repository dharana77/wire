package com.birely.wire.dto;

import com.birely.wire.entity.Account;
import lombok.Getter;

@Getter
public class DepositResponseDto {

  private Long accountId;

  private Long amount;

  public DepositResponseDto(Long accountId, Long amount) {
    this.accountId = accountId;
    this.amount = amount;
  }

  public static DepositResponseDto of(Account account) {
    return new DepositResponseDto(account.getId(), account.getDeposits());
  }
}
