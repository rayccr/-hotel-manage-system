package com.example.backend.service.impl.room;

import com.alibaba.fastjson2.JSONObject;
import com.example.backend.mapper.RoomMapper;
import com.example.backend.pojo.Room;
import com.example.backend.service.room.RoomAddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomAddServiceImpl implements RoomAddService {

    @Autowired
    private RoomMapper roomMapper;

    @Override
    public JSONObject roomAdd(String type, Integer price, Integer count, Float discount, String location, String info) {

        JSONObject resp = new JSONObject();
        Room room = new Room(null, price, count, discount, false, location, type, info);
        roomMapper.insert(room);

        resp.put("error_message", "success");
        return resp;
    }
}
