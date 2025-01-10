package com.birely.wire.dto;

import lombok.Getter;

@Getter
public class TransferRequestDto {

  private Long accountId;

  private Long receiptAccountId;

  private Long amount;
}
