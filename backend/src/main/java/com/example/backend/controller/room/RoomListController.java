package com.example.backend.controller.room;

import com.alibaba.fastjson2.JSONObject;
import com.example.backend.service.room.RoomListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoomListController {
    @Autowired
    private RoomListService roomListService;

    @PostMapping("/api/room/roomlist/")
    public JSONObject roomList(){
        return roomListService.roomList();
    }
}
