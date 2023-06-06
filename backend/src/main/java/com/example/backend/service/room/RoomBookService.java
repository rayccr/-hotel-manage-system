package com.example.backend.service.room;

import com.alibaba.fastjson2.JSONObject;

public interface RoomBookService {
    JSONObject roomBook(Integer roomId, Integer userId, Integer count, Integer days);
}
