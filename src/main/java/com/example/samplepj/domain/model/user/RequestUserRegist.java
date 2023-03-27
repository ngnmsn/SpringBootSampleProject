package com.example.samplepj.domain.model.user;

import lombok.Data;

@Data
public class RequestUserRegist {
  // ユーザ名
  private String userName;
  // パスワード
  private String password;
  // メールアドレス
  private String mailAddress;
}