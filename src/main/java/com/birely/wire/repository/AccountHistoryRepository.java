package com.birely.wire.repository;

import com.birely.wire.entity.AccountHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountHistoryRepository extends JpaRepository<AccountHistory, Long> {

  @Query(
    """
    SELECT AH FROM AccountHistory AH
    WHERE AH.accountId = :id
    AND AH.type IN ('이체', '출금')
    """
  )
  List<AccountHistory> findAllAccountHistoryByAccountIdInWithdrawAndTransfer(
    @Param("id") Long accountId);
}
