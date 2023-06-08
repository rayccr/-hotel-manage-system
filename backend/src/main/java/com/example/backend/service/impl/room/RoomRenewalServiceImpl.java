package com.example.backend.service.impl.room;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.backend.mapper.RoomBookMapper;
import com.example.backend.mapper.RoomMapper;
import com.example.backend.pojo.Room;
import com.example.backend.pojo.RoomBook;
import com.example.backend.service.room.RoomRenewalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomRenewalServiceImpl implements RoomRenewalService {

    @Autowired
    private RoomBookMapper roomBookMapper;

    @Autowired
    private RoomMapper roomMapper;

    @Override
    public JSONObject roomRenewal(Integer roomId, Integer userId, Integer days) {

        QueryWrapper<Room> queryWrapperRoom = new QueryWrapper<>();
        queryWrapperRoom.eq("id", roomId);
        Room room = roomMapper.selectOne(queryWrapperRoom);

        QueryWrapper<RoomBook> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("room_id", roomId).eq("user_id", userId);
        RoomBook roomBook = roomBookMapper.selectOne(queryWrapper);

        Float addCost = room.getPrice() * days * room.getDiscount();

        roomBook.setDays(roomBook.getDays() + days);
        roomBook.setCost(roomBook.getCost() + addCost);
        roomBookMapper.update(roomBook, queryWrapper);

        JSONObject resp = new JSONObject();
        resp.put("error_message", "success");
        return resp;
    }
}
