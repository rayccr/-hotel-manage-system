package com.example.backend.service.user;

import com.alibaba.fastjson2.JSONObject;

public interface RoomQueryService {
    JSONObject roomQueryService(String type, String location, Integer lowerPrice, Integer highPrice);
}
