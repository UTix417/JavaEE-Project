package com.example.demo.pojo;

import lombok.*;

import java.util.List;
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class pagein {
    private List<Page> pages;
    private int totalpage;
}
