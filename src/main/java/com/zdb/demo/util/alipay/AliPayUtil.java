package com.zdb.demo.util.alipay;

import com.alipay.api.*;
import com.alipay.api.domain.AlipayTradeQueryModel;
import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.*;
import com.alipay.api.response.*;
import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AliPayUtil {
    //app_id
    public static final String APP_ID = "2021001183607888";
    //回调地址
    public static final String REDIRECT_URI = "https://wechat.lvcchong.com";
    //支付宝网关
    public static final String URL = "https://openapi.alipay.com/gateway.do";
    public static final String FORMAT = "json";
    public static final String CHARSET = "UTF-8";
    public static final String SIGN_TYPE = "RSA2";
    //应用私钥
    public static final String APP_PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC6V5JKpS99LTrIxpR84eIyYzgYDVNqXuAq3w985LXg8rcGkQVf9LCNuRunbXL1amexPjL25uUdZAcgXEpeAiSZOcJGOpAXHZvDzWlTafBWb1z8XoDfFGQsq4wH9rDIAk+uSUSnvhGlbSlDsPv5LbI15bLG9+zF/z2Q4/QI81bvy3AeMZcMR5hoT4l3aJRDClpsa0W+tFi7tWP220HCwntyiOjnWD0ylurc56sFqVpAPop/U4h2oif7LyzRoLOXVux8iO4+mldXc4RANp/YcjRT7XNVfKum6RmcR1BCeKFRsepn1pp8tBqBNg+BQwDRD9qrUKFPuhtCic0joznmcIvXAgMBAAECggEBAKR3NDxDzUVLTU31zwrmB1H3toOsB0qkEHBmTfIqnsi7EIVwK/FEMyvzDBBbDpLC5mBOiIbYxbGKHqwWKOEOPSUsEGcRuylbzaYkv2iK4UA4rDZmZpH+Gmg+xKomtmLhf1hRfJIctNBwJ3KCW/LSvS7K3F62ZblfjQQ73aCkvb+IKea4VjlzU2EPaEZfqI50UPM22DPXd/HfpDQF9CzdaNk+aNJjBQc/bYL6Sww2pZVgro7Yv9Iswc97oL8GIqt0aeWE7Mr1Z244fVxSS+C2NbYl1YdRBey9qbN5iGey/w8tjoJVZGdATRp+JdIEln/e4oaX92Bimpzt2BCZs5ytv9ECgYEA56nCL3rnsFhM9hkFZ9EpuVmH4qccav9yda4SsKmxcyPIj3vPksbd+TZjcQG6BBRXCT9fqRHnud1h/Dp4wPWitpOjIO5T6QXweqrd5JxZmMAmAKCihsYjQPx8KbrS7X27L14TmTygViW3pPCw9YfhIumnwLWV7ckay2bsKubjId8CgYEAzer4N/8FTqeZIVAlQwQKB8+zk/npSL7X7/jV6f6rK4007ILziZW1Lb3nNXxN0+hZH6VZo+y4iTW+NkQNx4/VEgV+Tc2acZPS1gK8TBBPwoSfceeb31LYM8yHOovvH2eMHXjkse3CVw81x3KcUYVXH5QdxWCsOLM+11PTRZ6VBQkCgYAJ9hC1ffFscb+NbXIADsxefe0L3+em6ij/ymZfVkydCNLc6JP6Wba9uor4Fvzqo/1CFomEeZKfOVe3QsRid61xcFO5XMYa5Ph8LdCwvdBUcx6vNDkI1og5iSHFoZTkRV3BIahyFFtImQKWgwr448jYeXePH7+8o8MVL+3Dwn3NvQKBgGcvzOz6jWVJ1RF9gP6hIomhimKnxEAg6kKllkRoysvLOe8e+/1BGQZv+FG0FagP8cFK8a2UbTDMzqGQ3rrXF7WtLQq98hviMK/vtP4E2oMmRGQm5NQ3RoJGhqlPACfjkrIeJ7QF6fIIfDGS/ERZpjv71CNZe1P6do4ewmtLuW9JAoGBALIWfl6cqDjkciQzncrzyNPw5BLpelwDUjUKEN8V+kwe9xMv1ieBmDGPaxJym5f5WMXuhqWLki6E5kr0A9q2XcePGTJRV1nja9VocfIhly81WW5RpbApHv94vzNwJ3Z6An5O89pW75osK2Cm0WbK4LoGUI06OtWSPXYQe7YgcE5P";
    //支付宝公钥
    public static final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjMnKv1zloYim9XwpnS4kksrireEhzJkAvO88ULY372xgqiWM+an5rEXgxELUt0yB5KS1fm9f3uRVWQXJ6K0cZ7y99ZKl0LEZBpF0GOQ8fuDhlCMWay4l1Xiaf5vhIsNF9I7RcrfyL5fISoyFfNJj0/9XdKrhUfGPa4tHQwuBormr+Toe6XCfCz60xFFY9gVewO7qQV9/6q2imfX5t4hSfioI1W+EzUh4CTlMQ9BvfH3aDXy57C59i0lswsS1Q9uSpqUDPzltaPL4oO2cw+ih4ignQ9obVU15ufwRnXjQbG0wjtrtVU6cprheckrsTDZuQMDda9TYC/7Y/uDlK5eDLQIDAQAB";

    public static AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", APP_ID, APP_PRIVATE_KEY, "json", CHARSET, ALIPAY_PUBLIC_KEY, "RSA2");

    // 获取authcode
    // https://openauth.alipay.com/oauth2/publicAppAuthorize.htm?app_id=2021001183607888&scope=auth_user&redirect_uri=https://wechat.lvcchong.com

    /**
     * 获取access_token 和 user_id
     *
     * @param authCode
     * @return
     */
    public static Map<String, Object> getAccessToken(String authCode) {
        AlipaySystemOauthTokenRequest request = new AlipaySystemOauthTokenRequest();
        request.setCode(authCode);
        request.setGrantType("authorization_code");
        Map<String, Object> map = new HashMap<>();
        try {
            AlipaySystemOauthTokenResponse oauthTokenResponse = alipayClient.execute(request);
            map.put("code", oauthTokenResponse.getCode());
            map.put("accessToken", oauthTokenResponse.getAccessToken());
            //令牌有效期
            map.put("expiresIn", oauthTokenResponse.getExpiresIn());
            map.put("userId", oauthTokenResponse.getUserId());
            //刷新令牌有效期
            map.put("reExpiresIn", oauthTokenResponse.getReExpiresIn());
            //刷新令牌
            map.put("refreshToken", oauthTokenResponse.getRefreshToken());
            return map;
        } catch (AlipayApiException e) {
            //处理异常
            e.printStackTrace();
            map.put("errorMsg", e.getErrMsg());
            return map;
        }
    }

    /**
     * 获取用户基本信息
     *
     * @param accessToken
     * @return
     */
    public static Map<String, Object> getUser(String accessToken) {
        AlipayUserInfoShareRequest request = new AlipayUserInfoShareRequest();
        Map<String, Object> map = new HashMap<>();
        try {
            AlipayUserInfoShareResponse userinfoShareResponse = alipayClient.execute(request, accessToken);
            if (userinfoShareResponse.getCode().equals("10000")) {
                map.put("code", userinfoShareResponse.getCode());
                map.put("body", userinfoShareResponse.getBody());
                return map;
            } else {
                map.put("code", userinfoShareResponse.getCode());
                map.put("msg", userinfoShareResponse.getMsg());
            }
        } catch (AlipayApiException e) {
            //处理异常
            e.printStackTrace();
            map.put("errorMsg", e.getErrMsg());
            return map;
        }
        return map;
    }

    /**
     * h5支付
     *
     * @param aliPayEntity
     * @return
     * @throws AlipayApiException
     */
    public static Map<String, Object> h5Pay(AliPayEntity aliPayEntity) throws AlipayApiException {
        AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
        model.setBody(aliPayEntity.getBody());
        model.setSubject(aliPayEntity.getSubject());
        model.setTotalAmount(aliPayEntity.getTotalAmount());
        model.setOutTradeNo(aliPayEntity.getOutTradeNo());
        model.setProductCode("QUICK_WAP_WAY");
        model.setQuitUrl(aliPayEntity.getQuitUrl());

        AlipayTradeWapPayRequest request = new AlipayTradeWapPayRequest();
        request.setBizModel(model);
        request.setReturnUrl(aliPayEntity.getReturnUrl());// 设置支付完成同步回调地址
        request.setNotifyUrl(aliPayEntity.getNotifyUrl());//  设置支付完成异步通知接收地址
        //支付发起返回为html表单
        AlipayTradeWapPayResponse response = alipayClient.pageExecute(request);
        Map<String, Object> map = new HashMap<>();
        if (response.isSuccess()) {
            System.out.println("调用成功");
            map.put("response", response);
            return map;
        } else {
            System.out.println("调用失败");
            map.put("response", response);
            return map;
        }
    }

    /**
     * 查询订单
     *
     * @param aliPayEntity
     * @return
     * @throws AlipayApiException
     */
    public static Map<String, Object> query(AliPayEntity aliPayEntity) throws AlipayApiException {
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        AlipayTradeQueryModel model = new AlipayTradeQueryModel();
        model.setOutTradeNo(aliPayEntity.getOutTradeNo());
        request.setBizModel(model);
        AlipayTradeQueryResponse response = alipayClient.execute(request);
        Map<String, Object> map = new HashMap<>();
        if (response.isSuccess()) {
            System.out.println("调用成功");
            map.put("response", response);
            return map;
        } else {
            System.out.println("调用失败");
            map.put("response", response);
            return map;
        }
    }

    /**
     * 退款
     *
     * @param aliPayEntity
     * @return
     * @throws AlipayApiException
     */
    public static Map<String, Object> refund(AliPayEntity aliPayEntity) throws AlipayApiException {
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
        AlipayTradeRefundModel model = new AlipayTradeRefundModel();
        model.setOutRequestNo(aliPayEntity.getOutTradeNo());
        model.setTradeNo(aliPayEntity.getTradeNo());
        model.setRefundAmount(aliPayEntity.getTotalAmount());
        request.setReturnUrl("http://baidu.com");// 设置支付完成同步回调地址
        request.setNotifyUrl("http://baidu.com");//  设置支付完成异步通知接收地址
        request.setBizModel(model);
        AlipayTradeRefundResponse response = alipayClient.execute(request);
        Map<String, Object> map = new HashMap<>();
        if (response.isSuccess()) {
            System.out.println("调用成功");
            map.put("response", response);
            return map;
        } else {
            System.out.println("调用失败");
            map.put("response", response);
            return map;
        }
    }

    public static void main(String[] args) throws AlipayApiException {
        AliPayEntity aliPayEntity = new AliPayEntity();
        aliPayEntity.setTotalAmount("0.02");
        aliPayEntity.setOutTradeNo("202106081132");
        aliPayEntity.setQuitUrl("http://www.baidu.com");
        aliPayEntity.setReturnUrl("http://www.baidu.com");
//        aliPayEntity.setNotifyUrl("http://www.taobao.com/product/113714.html");
        Map<String, Object> map = AliPayUtil.h5Pay(aliPayEntity);//发起支付
        //返回的是html的form表单，加载到浏览器中会生成一个新的访问地址
        JSONObject mapResponse = JSONObject.fromObject(map.get("response"));
        System.out.println(mapResponse.getString("body"));
        Map<String, Object> query = AliPayUtil.query(aliPayEntity);//查询支付
        JSONObject object = JSONObject.fromObject(query.get("response"));
        aliPayEntity.setTradeNo(object.getString("tradeNo"));
//        aliPayEntity.setUser_id(object.getLong("buyerUserId"));

        Map<String, Object> map1 = AliPayUtil.refund(aliPayEntity);//退款
    }
}


