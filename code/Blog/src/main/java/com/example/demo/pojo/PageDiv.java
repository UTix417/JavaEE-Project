package com.example.demo.pojo;

import lombok.*;

import java.util.List;
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageDiv {
    private List<Text> texts;
    private int totalpage;
}
