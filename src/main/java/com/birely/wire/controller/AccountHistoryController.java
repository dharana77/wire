package com.birely.wire.controller;

import com.birely.wire.service.AccountHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/history")
@RestController
@RequiredArgsConstructor
public class AccountHistoryController {

  private final AccountHistoryService accountHistoryService;

  @GetMapping
  public ResponseEntity getTransferAndWithdrawAccountHistories(@RequestParam Long accountId) {
    return ResponseEntity.ok(accountHistoryService.getTransferAndWithdrawAccountHistories(accountId));
  }
}
