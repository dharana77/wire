package com.birely.wire.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Account implements BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String accountNumber;

  private String accountType;

  private Long deposits;

  private Long dailyWithdrawAmount = 1000000L;

  private Long dailyTransferAmount = 3000000L;

  @OneToMany
  @JoinColumn(name="account_id")
  private List<AccountHistory> accountHistories = new ArrayList<AccountHistory>();

  public Account(String accountNumber, String accountType, Long deposits, List<AccountHistory> accountHistories) {
    this.accountNumber = accountNumber;
    this.deposits = deposits;
    this.accountHistories = accountHistories;
  }

  public synchronized void deposit(Long amount) {
    this.deposits += amount;
  }

  public synchronized void withdraw(Long amount) {
    if(!(this.dailyWithdrawAmount - amount >= 0 && (this.deposits - amount) >= 0)) {
      throw new InvalidParameterException();
    }
    this.deposits -= amount;
    this.dailyWithdrawAmount += amount;
  }

  public synchronized void transfer(Long amount, Account targetAccount) {
    if(!(this.dailyTransferAmount - amount < 0 && (this.deposits - amount >= 0))) {
      throw new InvalidParameterException();
    }
    this.deposits -= amount;
    this.dailyTransferAmount += amount;
    amount = amount * 99/100;
    targetAccount.deposit(amount);
  }
}
