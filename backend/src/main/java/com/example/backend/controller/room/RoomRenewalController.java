package com.example.backend.controller.room;

import com.alibaba.fastjson2.JSONObject;
import com.example.backend.service.room.RoomRenewalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RoomRenewalController {
    @Autowired
    private RoomRenewalService roomRenewalService;

    @PostMapping("/api/room/roomrenewal/")
    public JSONObject roomRenewal(@RequestParam Map<String, String> data){
        Integer roomId = Integer.parseInt(data.get("roomId"));
        Integer userId = Integer.parseInt(data.get("userId"));
        Integer days = Integer.parseInt(data.get("days"));
        return roomRenewalService.roomRenewal(roomId, userId, days);
    }
}
