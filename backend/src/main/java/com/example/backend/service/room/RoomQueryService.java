package com.example.backend.service.room;

import com.alibaba.fastjson2.JSONObject;

public interface RoomQueryService {
    JSONObject roomQueryService(String type, String location, Float lowerPrice, Float highPrice);

    JSONObject roomQueryOneService(Integer id);
}
