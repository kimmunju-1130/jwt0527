package com.example.jwt0527.UserRepository;

import com.example.jwt0527.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity,String> {
    List<UserEntity> getByName(String name); // 중복된 이름 리스트로 반환

    UserEntity getByEmail(String email);

}
