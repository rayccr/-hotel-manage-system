package com.example.backend.controller.room;

import com.alibaba.fastjson2.JSONObject;
import com.example.backend.service.room.RoomPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RoomPayController {
    @Autowired
    private RoomPayService roomPayService;

    @PostMapping("/api/room/roompay/")
    public JSONObject roomPay(@RequestParam Map<String, String> data){
        Integer roomId = Integer.parseInt(data.get("roomId"));
        Integer userId = Integer.parseInt(data.get("userId"));

        System.out.println(roomId);
        System.out.println(userId);

        return roomPayService.roomPay(roomId, userId);
    }
}
