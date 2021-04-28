package com.zdb.demo.vo;

import lombok.Data;

import java.util.ArrayList;

@Data
public class AdvertisementVo {
    ArrayList<String> imgList;
    Integer storeId;
    Integer type;
}
