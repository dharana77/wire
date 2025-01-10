package com.birely.wire.dto;

import com.birely.wire.entity.Account;
import lombok.Getter;

@Getter
public class WithdrawResponseDto {

  private Long accountId;

  private Long amount;

  public WithdrawResponseDto(Long accountId, Long amount) {
    this.accountId = accountId;
    this.amount = amount;
  }

  public static WithdrawResponseDto of(Account account){
    return new WithdrawResponseDto(account.getId(), account.getDeposits());
  }
}
