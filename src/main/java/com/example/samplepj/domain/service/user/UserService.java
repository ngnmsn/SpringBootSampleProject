package com.example.samplepj.domain.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import com.example.samplepj.domain.model.user.User;
import com.example.samplepj.domain.repository.user.UserRepository;
import com.example.samplepj.domain.model.user.RequestUserRegist;
import com.example.samplepj.domain.model.user.ResponseUserRegist;
import com.example.samplepj.domain.model.user.RequestLogin;
import com.example.samplepj.domain.model.user.ResponseLogin;
import com.example.samplepj.util.user.PasswordUtil;
import java.util.List;
import java.util.ArrayList;

@Service
@Transactional
public class UserService {
  
  // リポジトリクラスの依存性注入
  @Autowired
  UserRepository userRepository;
  
  /**
   * ユーザ登録する情報のDBインサート処理
   * @param RequestUserRegist ユーザ登録APIのリクエストボディ
   * @return responseUserRegist ユーザ登録APIのレスポンスボディ
   */
  public ResponseUserRegist insertUser(RequestUserRegist requestUserRegist) {
    User user = new User();
    user = CreateUser(requestUserRegist);
    userRepository.save(user);
    ResponseUserRegist responseUserRegist = new ResponseUserRegist();
    responseUserRegist.setUserId(user.getUserId());
    responseUserRegist.setUserName(user.getUserName());
    responseUserRegist.setMailAddress(user.getMailAddress());
    return responseUserRegist;
  };

  /**
   * ユーザ登録するユーザ情報の作成処理
   * @param RequestUserRegist ユーザ登録APIのリクエストボディ
   * @return user ユーザ情報
   */
  private User CreateUser(RequestUserRegist requestUserRegist) {
    String hashPw;
    User user = new User();
    hashPw = PasswordUtil.hashSHA256(requestUserRegist.getPassword());
    user.setUserName(requestUserRegist.getUserName());
    user.setPassword(hashPw);
    user.setMailAddress(requestUserRegist.getMailAddress());
    
    return user;
  };

  /**
   * ログインするユーザ情報の作成処理
   * @param RequestLogin ログインAPIのリクエストボディ
   * @return responseLogin ログインAPIのレスポンスボディ
   */
  public ResponseLogin login(RequestLogin requestLogin) {
    User loginUser = new User();
    loginUser = CreateUser(requestLogin);
    List<User> userList = new ArrayList<User>();
    userList = userRepository.findByMailAddress(loginUser.getMailAddress());

    ResponseLogin responseLogin = new ResponseLogin();
    if ( userList.size() == 0) {
      responseLogin.setStatus("error");
    } else if ( !(loginUser.getPassword().equals(userList.get(0).getPassword() ))) {
      responseLogin.setStatus("error");
    } else {
      responseLogin.setStatus("success");
      responseLogin.setUserId(userList.get(0).getUserId());
      responseLogin.setUserName(userList.get(0).getUserName());
      responseLogin.setMailAddress(userList.get(0).getMailAddress());
    }
    return responseLogin;
  };

  /**
   * ログインするユーザ情報の作成処理
   * @param RequestLogin ログインAPIのリクエストボディ
   * @return user ユーザ情報
   */
  private User CreateUser(RequestLogin requestLogin) {
    String hashPw;
    User user = new User();
    hashPw = PasswordUtil.hashSHA256(requestLogin.getPassword());
    user.setPassword(hashPw);
    user.setMailAddress(requestLogin.getMailAddress());

    return user;
  };
}
