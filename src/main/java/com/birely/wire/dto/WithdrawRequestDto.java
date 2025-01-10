package com.birely.wire.dto;

import lombok.Getter;

@Getter
public class WithdrawRequestDto {

  private Long accountId;

  private Long amount;
}
