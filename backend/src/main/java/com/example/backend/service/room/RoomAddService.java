package com.example.backend.service.room;

import com.alibaba.fastjson2.JSONObject;

public interface RoomAddService {
    JSONObject roomAdd(String type, Integer price, Integer count, Float discount,
                       String location, String info);
}
