package com.example.jwt0527.service;

import com.example.jwt0527.UserRepository.UserRepository;
import com.example.jwt0527.model.UserEntity;
import com.example.jwt0527.model.UserRequestDto;
import com.example.jwt0527.model.UserResponseDto;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    public UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public void addUser(UserRequestDto userRequestDto){
        if(userRepository.findById(userRequestDto.getId()).isPresent()){
            throw new IllegalArgumentException("이미 존재하는 사용자입니다.");
        }
        UserEntity user = new UserEntity();
        user.setName(userRequestDto.getName());
        user.setEmail(userRequestDto.getEmail());
        user.setPw(passwordEncoder.encode(userRequestDto.getPw()));//암호 복호화
        userRepository.save(user);
    }
    public List<UserEntity> getUserByName(String name){
        List<UserEntity> getUser = userRepository.getByName(name);
        if (getUser.isEmpty()){
            System.out.println("회원정보가 없음");
            return null;
        }
        return getUser;
    }
    public List<UserEntity> getAllUsers(){
        List<UserEntity> getUsers = userRepository.findAll();
        return getUsers;
    }
    public String findPwByEmail(String email){
        UserEntity findPwByEmail = userRepository.getByEmail(email);
        if(findPwByEmail.getPw().isEmpty()) {
            return null;
        }
        return findPwByEmail.getPw();
    }
    public UserResponseDto updatePw(String id, String pw){
        Optional<UserEntity> updatePw = userRepository.findById(id);
        if(updatePw.isEmpty()){
            throw new IllegalArgumentException("사용자가 없습니다.") ; //예외구문임을 알려줌
        }
        UserEntity userPw = updatePw.get();
        userPw.setPw(pw);
        Optional<UserEntity> updatedPw = Optional.of(userRepository.save(userPw));
        return UserResponseDto.fromEntity(userPw);
    }

}
