package com.example.backend.controller.room;

import com.alibaba.fastjson2.JSONObject;
import com.example.backend.service.room.RoomBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RoomBookController {
    @Autowired
    private RoomBookService roomBookService;

    @PostMapping("/api/room/roombook/")
    public JSONObject roomBook(@RequestParam Map<String, String> data){
        Integer roomId = Integer.parseInt(data.get("roomId"));
        Integer userId = Integer.parseInt(data.get("userId"));
        Integer count = Integer.parseInt(data.get("count"));
        Integer days = Integer.parseInt(data.get("days"));
        return roomBookService.roomBook(roomId, userId, count, days);
    }
}
