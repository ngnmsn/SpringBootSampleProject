package com.example.samplepj.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.samplepj.domain.model.user.RequestUserRegist;
import com.example.samplepj.domain.model.user.ResponseUserRegist;
import com.example.samplepj.domain.service.user.UserService;

/**
 * UserControllerクラス
 * ユーザ関連のAPI
 */
@RestController
@RequestMapping("/user")
public class UserController {

  // サービスクラスの依存性注入
  @Autowired
  UserService userService;

  /**
   * ユーザ登録API 
   * POST /user/regist
   * @param requestUserRegist ユーザ登録APIのリクエストボディ
   * @return responseUserRegist ユーザ登録APIのレスポンスボディ
   */
  @PostMapping("regist")
  public ResponseUserRegist userRegist(@RequestBody RequestUserRegist requestUserRegist) {

      // サービスクラスのユーザ登録処理呼び出し
      ResponseUserRegist responseUserRegist = userService.insertUser(requestUserRegist);

      // APIレスポンス
      return responseUserRegist;
  }
}