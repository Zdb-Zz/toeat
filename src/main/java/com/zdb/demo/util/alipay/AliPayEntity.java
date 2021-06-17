package com.zdb.demo.util.alipay;

import lombok.Data;

/**
 * author zdb
 * time 2021年6月7日16:40:56
 * 支付宝支付实体类
 */
@Data
public class AliPayEntity {

    //令牌
    private String accessToken;

    //订单描述
    private String body;

    //支付测试标题
    private String subject;

    //商户网站唯一订单号
    private String outTradeNo;

    //支付宝交易号
    private String tradeNo;

    //订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]
    private String totalAmount;

    //用户付款中途退出返回商户网站的地址
    private String quitUrl;

    //支付完成同步回调地址
    private String returnUrl;

    //支付完成异步通知接收地址
    private String notifyUrl;

    //支付宝用户id
    private Long aliPayUserId;


}
