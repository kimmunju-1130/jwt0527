package com.example.jwt0527.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    private String id;
    private String pw;
    private String name;
    private String email;

    public UserResponseDto(UserEntity user) {
        this.id = user.getId();
        this.pw = user.getPw();
        this.name = user.getName();
        this.email = user.getEmail();
    }
    public static UserResponseDto fromEntity(UserEntity userEntity){
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.id = userEntity.getId();
        userResponseDto.pw = userEntity.getPw();
        userResponseDto.name = userEntity.getName();
        userResponseDto.email = userEntity.getEmail();
        return userResponseDto;
    }
}
