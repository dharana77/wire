package com.birely.wire.dto;

import com.birely.wire.entity.Account;
import lombok.Getter;

@Getter
public class TransferResponseDto {

  private Long accountId;

  private Long amount;

  private Long receiptAccountId;

  private Long receiptAccountAmount;

  public TransferResponseDto(Long accountId, Long amount, Long receiptAccountId, Long receiptAccountAmount) {
    this.accountId = accountId;
    this.amount = amount;
    this.receiptAccountId = receiptAccountId;
    this.receiptAccountAmount = receiptAccountAmount;
  }

  public static TransferResponseDto of(Account account, Account receipttAccount) {
    return new TransferResponseDto(account.getId(), account.getDeposits(), receipttAccount.getId(), receipttAccount.getDeposits());
  }
}
