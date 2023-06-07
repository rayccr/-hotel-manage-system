package com.example.backend.service.room;

import com.alibaba.fastjson2.JSONObject;

public interface RoomPayService {
    JSONObject roomPay(Integer roomId, Integer userId);
}
