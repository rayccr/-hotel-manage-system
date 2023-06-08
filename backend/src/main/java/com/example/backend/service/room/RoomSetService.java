package com.example.backend.service.room;

import com.alibaba.fastjson2.JSONObject;

public interface RoomSetService {
    JSONObject roomSet(Integer id, String location, String type, Float price, Integer count, Float discount, String info);
}
