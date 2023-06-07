package com.example.backend.controller.user;

import com.alibaba.fastjson2.JSONObject;
import com.example.backend.service.user.RoomBookInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RoomBookInfoController {
    @Autowired
    private RoomBookInfoService roomBookInfoService;

    @PostMapping("/api/user/roombookinfo/")
    public JSONObject roomBookInfo(@RequestParam Map<String, String> data){
        Integer userId = Integer.parseInt(data.get("userId"));
        return roomBookInfoService.roomBookInfo(userId);
    }

}
