package com.example.backend.controller.user;

import com.alibaba.fastjson2.JSONObject;
import com.example.backend.service.user.RoomListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RoomListController {
    @Autowired
    private RoomListService roomListService;

    @PostMapping("/api/user/roomlist/")
    public JSONObject roomList(){
        return roomListService.roomList();
    }
}
