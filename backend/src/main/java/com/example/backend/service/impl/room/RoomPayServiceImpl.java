package com.example.backend.service.impl.room;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.backend.mapper.RoomBookMapper;
import com.example.backend.pojo.RoomBook;
import com.example.backend.service.room.RoomBookService;
import com.example.backend.service.room.RoomPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomPayServiceImpl implements RoomPayService {

    @Autowired
    private RoomBookMapper roomBookMapper;
    @Override
    public JSONObject roomPay(Integer roomId, Integer userId) {
        QueryWrapper<RoomBook> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("room_id", roomId).eq("user_id", userId);

        RoomBook roomBook = roomBookMapper.selectOne(queryWrapper);

        roomBook.setState(true);
        roomBookMapper.update(roomBook, queryWrapper);

        JSONObject resp = new JSONObject();
        resp.put("error_message", "success");
        return resp;
    }
}
