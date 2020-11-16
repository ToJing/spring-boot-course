package com.to.jing.course.sdk.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private Integer id;
    private String username;
    private String password;
    private Integer age;
    private Boolean disable;

    public static User Null(){
        User user = new User();
        user.setDisable(false);
        return user;
    }

}
