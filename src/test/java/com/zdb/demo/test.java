package com.zdb.demo;

import com.zdb.demo.util.TokenUtil;

import java.util.Map;

public class test {
    public static void main(String[] args) throws InterruptedException {
        test t = new test();
        t.testHS256();
    }

    public void testHS256() throws InterruptedException {
        String token = TokenUtil.buildJWT("account123");
        System.out.println(token);
        //解密token
        Map<String, String> account = TokenUtil.vaildToken(token);
        System.out.println("校验token成功，token的账号："+account);

        //测试过期
        Thread.sleep(10 * 1000);
        account = TokenUtil.vaildToken(token);
        System.out.println(account);
    }
}
