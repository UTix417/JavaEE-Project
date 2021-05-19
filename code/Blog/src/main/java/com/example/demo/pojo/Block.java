package com.example.demo.pojo;

import lombok.*;

/**
 * @author 朱威
 * @create 2021-05-12-11:40
 */
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Block {
    private Integer blockId;
    private String blockName;
    private Integer blockLevel;
    private Integer blockNumber;
}
