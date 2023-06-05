package com.example.backend.controller.user;

import com.alibaba.fastjson2.JSONObject;
import com.example.backend.service.user.RoomAddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Objects;

@RestController
public class RoomAddController {
    @Autowired
    private RoomAddService roomAddService;

    @PostMapping("/api/user/roomadd/")
    public JSONObject roomAdd(@RequestParam MultiValueMap<String, String> data){
        String type = data.getFirst("type");
        Integer price = Integer.parseInt(Objects.requireNonNull(data.getFirst("price")));
        Integer count = Integer.parseInt(Objects.requireNonNull(data.getFirst("count")));
        Float discount = Float.parseFloat(Objects.requireNonNull(data.getFirst("discount")));
        String location = data.getFirst("location");
        String info = data.getFirst("info");
        return roomAddService.roomAdd(type, price, count, discount, location,info);
    }

}
