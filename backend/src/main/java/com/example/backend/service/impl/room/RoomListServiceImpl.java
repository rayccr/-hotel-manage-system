package com.example.backend.service.impl.room;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.backend.mapper.RoomMapper;
import com.example.backend.pojo.Room;
import com.example.backend.service.room.RoomListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomListServiceImpl implements RoomListService {

    @Autowired
    private RoomMapper roomMapper;

    @Override
    public JSONObject roomList() {
        QueryWrapper<Room> queryWrapper = new QueryWrapper<>();
        queryWrapper.ne("id", -1);

        List<Room> rooms = roomMapper.selectList(queryWrapper);
        JSONObject resp = new JSONObject();
        resp.put("rooms", rooms);

        return resp;
    }
}
