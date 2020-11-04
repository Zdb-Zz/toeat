package com.zdb.demo.service;

import com.mysql.cj.xdevapi.JsonArray;
import com.zdb.demo.entity.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface OrderService {
    Boolean addOrder(JSONObject object, User user);
}
