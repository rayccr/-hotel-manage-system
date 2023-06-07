package com.example.backend.service.impl.user;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.backend.mapper.RoomBookMapper;
import com.example.backend.mapper.RoomMapper;
import com.example.backend.pojo.Room;
import com.example.backend.pojo.RoomBook;
import com.example.backend.service.user.RoomBookInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomBookInfoServiceImpl implements RoomBookInfoService {

    @Autowired
    private RoomBookMapper roomBookMapper;

    @Autowired
    private RoomMapper roomMapper;

    @Override
    public JSONObject roomBookInfo(Integer userId) {
        QueryWrapper<RoomBook> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("user_id", userId);
        List<RoomBook> res = roomBookMapper.selectList(queryWrapper);

        List<Room> rooms = new ArrayList<>();
        Room room;

        for (RoomBook re : res) {
            QueryWrapper<Room> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("id", re.getRoomId());
            room = roomMapper.selectOne(queryWrapper1);
            rooms.add(room);
        }

        JSONObject resp = new JSONObject();
        resp.put("rooms", rooms);
        resp.put("roomBookInfo", res);
        return resp;
    }
}
