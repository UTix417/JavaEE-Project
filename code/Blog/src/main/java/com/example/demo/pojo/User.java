
package com.example.demo.pojo;

import lombok.*;

import java.util.Date;

/**
 * @author 朱威
 * @create 2021-05-12-11:35
 */
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer userId;
    private String userName;
    private String userPassword;
    private Integer userLevel;
    private Integer userState;
    private Date userCreateTime;
    private String userImagePath;

    public User(String userId, String userName, Object userPassword, Object userLevel, Object userState, Object userCreateTime, String img) {
    }
}
