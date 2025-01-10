package com.birely.wire.controller;

import com.birely.wire.dto.DeleteAccountRequestDto;
import com.birely.wire.dto.DepositRequestDto;
import com.birely.wire.dto.DepositResponseDto;
import com.birely.wire.dto.EnrollAccountRequestDto;
import com.birely.wire.dto.EnrollAccountResponseDto;
import com.birely.wire.dto.HistoryRequestDto;
import com.birely.wire.dto.HistoryResponseDto;
import com.birely.wire.dto.TransferRequestDto;
import com.birely.wire.dto.TransferResponseDto;
import com.birely.wire.dto.WithdrawRequestDto;
import com.birely.wire.dto.WithdrawResponseDto;
import com.birely.wire.repository.AccountHistoryRepository;
import com.birely.wire.service.AccountHistoryService;
import com.birely.wire.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

  private final AccountService accountService;

  @PostMapping("/enroll")
  public ResponseEntity enrollAccount(@RequestBody EnrollAccountRequestDto enrollAccountRequestDto) {
    EnrollAccountResponseDto res = accountService.enrollAccount(enrollAccountRequestDto);
    return ResponseEntity.ok(res);
  }

  @DeleteMapping("/delete")
  public ResponseEntity deleteAccount(@RequestBody DeleteAccountRequestDto deleteAccountRequestDto) {
    accountService.deleteAccount(deleteAccountRequestDto);
    return ResponseEntity.noContent().build();
  }

  @PostMapping("/deposit")
  public ResponseEntity deposit(@RequestBody DepositRequestDto depositRequestDto) {
    DepositResponseDto res = accountService.deposit(depositRequestDto);
    return ResponseEntity.ok(res);
  }

  @PostMapping("/withdraw")
  public ResponseEntity withdraw(@RequestBody WithdrawRequestDto withdrawRequestDto) {
    WithdrawResponseDto res = accountService.withdraw(withdrawRequestDto);

    return ResponseEntity.ok(res);
  }

  @PostMapping("/transfer")
  public ResponseEntity transfer(@RequestBody TransferRequestDto transferRequestDto) {
    TransferResponseDto res = accountService.transfer(transferRequestDto);
    return ResponseEntity.ok(res);
  }
}
