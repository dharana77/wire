package com.birely.wire.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class AccountHistory implements BaseEntity{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name="account_id")
  private Long accountId;

  private Long targetAccountId;

  private String type;

  private Long amount;

  public AccountHistory(Long accountId, Long targetAccountId, String type, Long amount) {
    this.accountId = accountId;
    this.targetAccountId = targetAccountId;
    this.type = type;
    this.amount = amount;
  }
}
