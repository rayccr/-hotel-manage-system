package com.example.backend.service.room;

import com.alibaba.fastjson2.JSONObject;

public interface RoomRenewalService {
    JSONObject roomRenewal(Integer roomId, Integer userId,Integer days);
}