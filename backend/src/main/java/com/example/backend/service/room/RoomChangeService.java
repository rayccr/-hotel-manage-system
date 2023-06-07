package com.example.backend.service.room;

import com.alibaba.fastjson2.JSONObject;

public interface RoomChangeService {
    JSONObject roomChange(Integer roomId, Integer newRoomId, Integer userId, Integer count, Integer days);
}
