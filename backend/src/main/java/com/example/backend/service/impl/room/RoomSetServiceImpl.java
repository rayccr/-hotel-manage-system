package com.example.backend.service.impl.room;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.backend.mapper.RoomMapper;
import com.example.backend.pojo.Room;
import com.example.backend.service.room.RoomSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomSetServiceImpl implements RoomSetService {

    @Autowired
    private RoomMapper roomMapper;
    @Override
    public JSONObject roomSet(Integer id, String location, String type, Float price,
                              Integer count, Float discount, String info) {
        QueryWrapper<Room> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);

        Room room = roomMapper.selectOne(queryWrapper);

        room.setLocation(location);
        room.setType(type);
        room.setPrice(price);
        room.setCount(count);
        room.setDiscount(discount);
        room.setInfo(info);
        roomMapper.update(room, queryWrapper);

        JSONObject resp = new JSONObject();
        resp.put("error_message", "success");

        return resp;
    }
}
