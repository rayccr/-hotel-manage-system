package com.example.backend.service.room;

import com.alibaba.fastjson2.JSONObject;

public interface RoomQueryService {
    JSONObject roomQueryService(String type, String location, Integer lowerPrice, Integer highPrice);

    JSONObject roomQueryOneService(Integer id);
}
