package com.example.samplepj.domain.repository.user;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.samplepj.domain.model.user.User;
// import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // List<User> findByMailAddress(String mailAddress);
}