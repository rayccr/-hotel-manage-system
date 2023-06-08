package com.example.backend.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Float price;
    private Integer count;
    private Float discount;
    private String location;
    private String type;
    private String info;
}
