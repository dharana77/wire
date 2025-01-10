package com.birely.wire.dto;

import lombok.Getter;

@Getter
public class HistoryResponseDto {

  private String type;

  private Long receiptAccountId;

  private Long amount;

  public HistoryResponseDto(String type, Long receiptAccountId, Long amount) {
    this.type = type;
    this.receiptAccountId = receiptAccountId;
    this.amount = amount;
  }
}
