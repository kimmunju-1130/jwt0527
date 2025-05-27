package com.example.jwt0527.controller;

import com.example.jwt0527.model.UserRequestDto;
import com.example.jwt0527.model.UserResponseDto;
import com.example.jwt0527.service.UserService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    public UserService userService;

    //회원이름으로 회원조회
    @GetMapping("/get")
    public List<UserResponseDto> getUserByName(@RequestParam String name) {
        return  userService.getUserByName(name).stream().map(user -> new UserResponseDto(user)).
                collect(Collectors.toList());
        /* 서비스에서 UserEntity로 되어 있기때문에 Entity 노출을 최소화해야 하므로 형변환이 필요. stream().map()을 사용할 것임.
        user.Service.getUserByName(name)의 결과값을 UserEntity user(list형태)에 넣어주고 그것을 UserResponseDto user 로 변환.
        collect 모아서 List로 반환.
         */
    }
    //전체회원 조회
    @GetMapping("/getAll")
    public List<UserResponseDto> getAllUsers(){
        return userService.getAllUsers().stream()
                .map(user -> new UserResponseDto(user))
                .collect(Collectors.toList());
    }
    //특정 이메일 조회
    @GetMapping("/email")
    public List<UserResponseDto> filterByEmailDomain(@RequestParam("domain") String domain){
        return userService.getAllUsers().stream()
                .filter(user-> user.getEmail().endsWith(domain))
                .map(UserResponseDto::new)
                .collect(Collectors.toList());
    }
    //이메일로 비밀번호 찾기
    @GetMapping("/findpw")
    public String findByEmail(@RequestParam String email){
        String findByEmail = userService.findPwByEmail(email);
        return findByEmail;
    }
    //회원추가
    @PostMapping("/add")
    public ResponseEntity<?> signup(@RequestBody UserRequestDto userRequestDto) {
        userService.addUser(userRequestDto);
        return ResponseEntity.ok("회원가입 성공");
    }
    //비밀번호 변경
    @PutMapping("/updatepw/{id}/password")
    public UserResponseDto updatePassword(@PathVariable(value = "id") String id, @RequestParam(value = "newPw") String newPw){
        return userService.updatePw(id,newPw);
    }


}
