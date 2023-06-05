package com.example.backend.controller.user;

import com.alibaba.fastjson2.JSONObject;
import com.example.backend.service.user.RoomQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RoomQueryController {
    @Autowired
    private RoomQueryService roomQueryService;

    @PostMapping("/api/user/roomquery/")
    public JSONObject roomQuery(@RequestParam Map<String, String> data){
        String type = data.get("type");
        String location = data.get("location");
        Integer lowerPrice = Integer.parseInt(data.get("lowerPrice"));
        Integer highPrice = Integer.parseInt(data.get("highPrice"));
        return roomQueryService.roomQueryService(type, location, lowerPrice, highPrice);
    }
}
