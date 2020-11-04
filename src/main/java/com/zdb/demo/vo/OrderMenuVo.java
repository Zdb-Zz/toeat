package com.zdb.demo.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderMenuVo {
    private int menuId;
    private int menuNum;
    private BigDecimal menuPrice;
}
