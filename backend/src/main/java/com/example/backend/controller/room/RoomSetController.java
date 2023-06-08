package com.example.backend.controller.room;

import com.alibaba.fastjson2.JSONObject;
import com.example.backend.service.room.RoomSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RoomSetController {
    @Autowired
    private RoomSetService roomSetService;

    @PostMapping("/api/room/roomset/")
    public JSONObject roomSet(@RequestParam Map<String, String> data){
        Integer id = Integer.parseInt(data.get("id"));
        String location = data.get("location");
        String type = data.get("type");
        Float price = Float.parseFloat(data.get("price"));
        Integer count = Integer.parseInt(data.get("count"));
        Float discount = Float.parseFloat(data.get("discount"));
        String info = data.get("info");
        return roomSetService.roomSet(id, location, type, price, count, discount, info);
    }
}
