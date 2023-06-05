package com.example.backend.service.user.account;

import com.alibaba.fastjson2.JSONObject;

import java.util.Map;

public interface LoginService {
    JSONObject getToken(String username, String password);

}
