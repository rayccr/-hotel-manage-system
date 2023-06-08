package com.example.backend.service.impl.room;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.backend.mapper.RoomBookMapper;
import com.example.backend.mapper.RoomMapper;
import com.example.backend.pojo.Room;
import com.example.backend.pojo.RoomBook;
import com.example.backend.service.room.RoomBookService;
import com.example.backend.service.room.RoomChangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class RoomChangeServiceImpl implements RoomChangeService {

    @Autowired
    private RoomBookMapper roomBookMapper;

    @Autowired
    private RoomMapper roomMapper;
    @Override
    public JSONObject roomChange(Integer roomId, Integer newRoomId, Integer userId, Integer count, Integer days) {

        JSONObject resp = new JSONObject();

        QueryWrapper<Room> queryWrapperRoom = new QueryWrapper<>();
        queryWrapperRoom.eq("id", newRoomId);
        Room newRoom = roomMapper.selectOne(queryWrapperRoom);

        if(newRoom.getCount() < count){
            resp.put("error_message", "房间不足");
            return resp;
        }

        newRoom.setCount(newRoom.getCount() - count);
        roomMapper.update(newRoom, queryWrapperRoom);

        Float addCost = newRoom.getPrice() * days * count * newRoom.getDiscount();

        QueryWrapper<RoomBook> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("room_id", newRoomId).eq("user_id", userId); // 如果已经有，要累加
        RoomBook roomBook = roomBookMapper.selectOne(queryWrapper);

        if(roomBook != null){
            roomBook.setCost(roomBook.getCost() + addCost);
            roomBook.setDays(roomBook.getDays() + days);
            roomBook.setCount(roomBook.getCount() + count);
            roomBookMapper.update(roomBook, queryWrapper);
            resp.put("error_message", "success");
            return resp;
        }


        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String datetmp = formatter.format(date);

        RoomBook roomBook1 = new RoomBook(newRoomId, userId, count, days, datetmp, addCost, false);
        roomBookMapper.insert(roomBook1);

        resp.put("error_message", "success");
        return resp;
    }
}
