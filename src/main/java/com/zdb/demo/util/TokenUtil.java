package com.zdb.demo.util;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TokenUtil {
    /**
     * 创建秘钥
     */
    private static final byte[] SECRET = "6MNSobBRCHGIO0fS6MNSobBRCHGIO0fS".getBytes();

    /**
     * 过期时间5秒
     */
    private static final long EXPIRE_TIME = 1000 * 60*60;


    /**
     * 生成Token
     * @param account
     * @return
     */
    public static String buildJWT(String account) {
        try {
            /**
             * 1.创建一个32-byte的密匙
             */
            MACSigner macSigner = new MACSigner(SECRET);
            /**
             * 2. 建立payload 载体
             */
            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                    .subject("doi")
                    .issuer("http://www.doiduoyi.com")
                    .expirationTime(new Date(System.currentTimeMillis() + EXPIRE_TIME))
                    .claim("ACCOUNT",account)
                    .build();

            /**
             * 3. 建立签名
             */
            SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
            signedJWT.sign(macSigner);

            /**
             * 4. 生成token
             */
            String token = signedJWT.serialize();
            return token;
        } catch (KeyLengthException e) {
            e.printStackTrace();
        } catch (JOSEException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 校验token
     * @param token
     * @return
     */
    public static Map<String,String> vaildToken(String token ) {
        Map<String, String> result = new HashMap<>();
        Object account = new Object();
        try {
            SignedJWT jwt = SignedJWT.parse(token);
            JWSVerifier verifier = new MACVerifier(SECRET);
            //校验是否有效
            if (!jwt.verify(verifier)) {
                result.put("code","-1");
                result.put("data","Token 无效");
                return  result;
            }

            //校验超时
            Date expirationTime = jwt.getJWTClaimsSet().getExpirationTime();
            if (new Date().after(expirationTime)) {
                result.put("code","-2");
                result.put("data","Token 已过期");
                return  result;
            }

            //获取载体中的数据
            account = jwt.getJWTClaimsSet().getClaim("ACCOUNT");
            //是否有openUid
            if (Objects.isNull(account)){
                result.put("code","-3");
                result.put("data","账号为空");
                return  result;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (JOSEException e) {
            e.printStackTrace();
        }
        result.put("code","1");
        result.put("data","校验成功");
        result.put("account", String.valueOf(account));
        return  result;
    }
//    eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkb2kiLCJBQ0NPVU5UIjoiYWNjb3VudDEyMyIsImlzcyI6Imh0dHA6XC9cL3d3dy5kb2lkdW95aS5jb20iLCJleHAiOjE2MjIwODgzMjV9.K8kUECE6A-NUNmelUnAg2YxjKJSAYkBpjdsKK9EHeZw
//    eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkb2kiLCJBQ0NPVU5UIjoiYWNjb3VudDEyMyIsImlzcyI6Imh0dHA6XC9cL3d3dy5kb2lkdW95aS5jb20iLCJleHAiOjE2MjIwODgzODB9.rqJMQil27EQChRxi81U33_TLo0Scc0mpIwxagoM51Rg

}
