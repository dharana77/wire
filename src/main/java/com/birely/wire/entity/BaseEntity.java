package com.birely.wire.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;


public interface BaseEntity {

  @CreatedDate
  LocalDateTime createdAt = LocalDateTime.now();

  @LastModifiedDate
  LocalDateTime updatedAt = LocalDateTime.now();
}
