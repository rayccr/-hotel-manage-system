package com.example.backend.service.impl.room;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.backend.mapper.RoomBookMapper;
import com.example.backend.mapper.RoomMapper;
import com.example.backend.mapper.UserMapper;
import com.example.backend.pojo.Room;
import com.example.backend.pojo.RoomBook;
import com.example.backend.pojo.User;
import com.example.backend.service.room.RoomBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class RoomBookServiceImpl implements RoomBookService {

    @Autowired
    private RoomBookMapper roomBookMapper;

    @Autowired
    private RoomMapper roomMapper;

    @Override
    public JSONObject roomBook(Integer roomId, Integer userId, Integer count, Integer days) {
//        LambdaQueryWrapper<RoomBook> wrapper = new LambdaQueryWrapper<>(); // 多表查询

        QueryWrapper<Room> queryWrapperRoom = new QueryWrapper<>();
        queryWrapperRoom.eq("id", roomId);
        Room room = roomMapper.selectOne(queryWrapperRoom);


        JSONObject resp = new JSONObject();

        if(room.getCount() < count){
            resp.put("error_message", "房间数量不足");
            return resp;
        }

        Integer cost = count * days * room.getPrice();
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String datetmp = formatter.format(date);

        RoomBook roomBook = new RoomBook(roomId, userId, count, datetmp, cost);

        room.setCount(room.getCount() - count);
        roomMapper.update(room, queryWrapperRoom);

        roomBookMapper.insert(roomBook);

        resp.put("error_message", "success");

        return resp;
    }
}
