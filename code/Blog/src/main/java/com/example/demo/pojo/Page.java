package com.example.demo.pojo;

import lombok.*;

import java.util.Date;

/**
 * @author 朱威
 * @create 2021-05-12-14:19
 */
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Page {
    private Integer textId;
    private Integer pageFloor;
    private Date pageRetime;
    private String pageContent;
    private User user;
}
