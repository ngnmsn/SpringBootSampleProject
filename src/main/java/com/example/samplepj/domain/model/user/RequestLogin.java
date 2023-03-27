package com.example.samplepj.domain.model.user;

import lombok.Data;

@Data
public class RequestLogin {
  // メールアドレス
  private String mailAddress;
  // パスワード
  private String password;
}