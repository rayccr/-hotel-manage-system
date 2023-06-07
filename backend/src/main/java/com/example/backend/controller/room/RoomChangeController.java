package com.example.backend.controller.room;

import com.alibaba.fastjson2.JSONObject;
import com.example.backend.service.room.RoomChangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RoomChangeController {

    @Autowired
    private RoomChangeService roomChangeService;

    @PostMapping("/api/room/roomchange/")
    public JSONObject roomChange(@RequestParam Map<String, String> data){
        Integer roomId = Integer.parseInt(data.get("roomId"));
        Integer newRoomId = Integer.parseInt(data.get("newRoomId"));
        Integer userId = Integer.parseInt(data.get("userId"));
        Integer count = Integer.parseInt(data.get("count"));
        Integer days = Integer.parseInt(data.get("days"));
        return roomChangeService.roomChange(roomId, newRoomId, userId, count, days);
    }
}
