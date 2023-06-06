package com.example.backend.controller.room;

import com.alibaba.fastjson2.JSONObject;
import com.example.backend.service.room.RoomQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RoomQueryController {
    @Autowired
    private RoomQueryService roomQueryService;

    @PostMapping("/api/room/roomquery/")
    public JSONObject roomQuery(@RequestParam Map<String, String> data){
        String type = data.get("type");
        String location = data.get("location");
        Integer lowerPrice = Integer.parseInt(data.get("lowerPrice"));
        Integer highPrice = Integer.parseInt(data.get("highPrice"));
        return roomQueryService.roomQueryService(type, location, lowerPrice, highPrice);
    }

    @PostMapping("/api/room/roomqueryone/")
    public JSONObject roomQueryOneService(@RequestParam Map<String, String> data){
        Integer id = Integer.parseInt(data.get("id"));
        return roomQueryService.roomQueryOneService(id);
    }
}
