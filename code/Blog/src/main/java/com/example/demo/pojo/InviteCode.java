package com.example.demo.pojo;

import lombok.*;

import java.util.Date;

/**
 * @author 朱威
 * @create 2021-05-12-16:05
 */
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InviteCode {
    private Integer inviteCodeId;
    private String inviteCodeContent;
    private Integer inviteCodeState;//0已经被使用，1还没被使用
    private Integer creatorId;//创建者ID
    private Integer userId;//使用者ID
    private Date inviteCodeDate;//使用时间
}
