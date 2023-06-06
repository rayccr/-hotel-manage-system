package com.example.backend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomBook {
    private Integer roomId;
    private Integer userId;
    private Integer days;
    private String startDay;
    private Integer cost;
}
