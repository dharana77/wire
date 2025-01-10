package com.birely.wire.dto;

import com.birely.wire.entity.Account;
import lombok.Getter;

@Getter
public class EnrollAccountResponseDto {

  private Long id;
  private String number;
  private String type;
  private Long deposits;

  public EnrollAccountResponseDto(Long id, String number, String type, Long deposits) {
    this.id = id;
    this.number = number;
    this.type = type;
    this.deposits = deposits;
  }

  public static EnrollAccountResponseDto of(Account account) {
    return new EnrollAccountResponseDto(
      account.getId(), account.getAccountNumber(), account.getAccountType(), account.getDeposits());
  }
}
