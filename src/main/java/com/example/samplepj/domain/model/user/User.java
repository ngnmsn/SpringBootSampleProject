package com.example.samplepj.domain.model.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "user_table")
@Data
public class User {
  // ユーザID
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private String userId;
  // ユーザ名
  private String userName;
  // パスワード
  private String password;
  // メールアドレス
  private String mailAddress;
}