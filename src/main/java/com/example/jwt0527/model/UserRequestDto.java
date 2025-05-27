package com.example.jwt0527.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {
    private String id;
    private String pw;
    private String name;
    private String email;

    public UserEntity toEntity(){
        return new UserEntity(this.id,this.pw,this.name,this.email);
    }

}
