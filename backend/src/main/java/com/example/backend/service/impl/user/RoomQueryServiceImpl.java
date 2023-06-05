package com.example.backend.service.impl.user;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.backend.mapper.RoomMapper;
import com.example.backend.pojo.Room;
import com.example.backend.service.user.RoomQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class RoomQueryServiceImpl implements RoomQueryService {

    @Autowired
    private RoomMapper roomMapper;

    @Override
    public JSONObject roomQueryService(String type, String location, Integer lowerPrice, Integer highPrice) {

        QueryWrapper<Room> queryWrapper = new QueryWrapper<>();
        if(!Objects.equals(type, "-1")){
            queryWrapper.eq("type", type);
        } if(!Objects.equals(location, "-1")){
            queryWrapper.eq("location", location);
        } if(lowerPrice != -1){
            queryWrapper.ge("price", lowerPrice);
        } if(highPrice != -1){
            queryWrapper.le("price", highPrice);
        }

//        List<Room> res =  roomMapper.selectList(null); // 全选
        List<Room> res = roomMapper.selectList(queryWrapper);

        JSONObject resp = new JSONObject();
        resp.put("rooms", res);

        return resp;
    }
}
