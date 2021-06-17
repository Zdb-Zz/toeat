package com.zdb.demo.interceptor;

import com.zdb.demo.entity.User;
import com.zdb.demo.service.UserService;
import com.zdb.demo.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;

/**
 * 后台用户登陆拦截器
 *
 * @author Administrator
 *
 */
@Slf4j
@Component
public class LoginBackUserInterceptor implements HandlerInterceptor {

	/** 编码格式 **/
	private static final String ENCODING = "UTF-8";

	/** 请求方式 **/
	private static String HttpMethod = "GET";


	@Resource
	UserService userService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		JSONObject json = new JSONObject();

		String path = request.getServletPath();
		// 拦截路径login, logout不用拦截
		if (path.equals("/user/login")) {
			return true;
		} else {
			// 获取用户名渠道和全局会话

			String userName = request.getHeader("userName");
			Integer userId = Integer.valueOf(request.getHeader("userId"));
			String token = request.getHeader("token");
			log.info("token:" + token);
			if (token==null){
				json.put("code", "-1");
				json.put("message", "Token is null");
				PrintWriter writer = response.getWriter();
				response.setCharacterEncoding("UTF-8");
				writer.print(json.toString());
				writer.close();
				response.flushBuffer();
				return false;
			}
			User user = userService.findUser(userId);
			Map<String, String> map = TokenUtil.vaildToken(token);
			Integer code = Integer.valueOf(map.get("code"));
			String account = map.get("account");
			if (code == 1){
				if (account.equals(user.getUserName())){
					return true;
				}else {
					return false;
				}
			}else if(code==-1){
				json.put("code", "-1");
				json.put("message", "Token无效");
				PrintWriter writer = response.getWriter();
				response.setCharacterEncoding("UTF-8");
				writer.print(json.toString());
				writer.close();
				response.flushBuffer();
				return false;
			}else if (code==-2){
				json.put("code", "-2");
				json.put("message", "Token 已过期");
				PrintWriter writer = response.getWriter();
				response.setCharacterEncoding("UTF-8");
				writer.print(json.toString());
				writer.close();
				response.flushBuffer();
				return false;
			}else {
				json.put("code", "-3");
				json.put("message", "账号为空");
				PrintWriter writer = response.getWriter();
				response.setCharacterEncoding("UTF-8");
				writer.print(json.toString());
				writer.close();
				response.flushBuffer();
				return false;
			}
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	/**
	 * 根据传入参数生成签名字符串
	 *
	 * @param parameters
	 * @return
	 * @throws Exception
	 */
	public static String computeSignature(Map<String, String> parameters, String AccessKeySecret) throws Exception {
		// 将参数Key按字典顺序排序
		String[] sortedKeys = parameters.keySet().toArray(new String[] {});
		Arrays.sort(sortedKeys);

		final String SEPARATOR = "&";

		// 生成规范化请求字符串
		StringBuilder canonicalizedQueryString = new StringBuilder();
		for (String key : sortedKeys) {
			canonicalizedQueryString.append("&").append(percentEncode(key)).append("=")
					.append(percentEncode(parameters.get(key)));
		}

		// 生成用于计算签名的字符串 stringToSign
		StringBuilder stringToSign = new StringBuilder();
		stringToSign.append(HttpMethod).append(SEPARATOR);
		stringToSign.append(percentEncode("/")).append(SEPARATOR);
		stringToSign.append(percentEncode(canonicalizedQueryString.toString().substring(1)));

		// 注意accessKeySecret后面要加入一个字符"&"
		String signature = calculateSignature(AccessKeySecret + "&", stringToSign.toString());
		log.info(signature.replaceAll("/", "%2F").replaceAll("\\+", "%2B").replaceAll("=", "%3D"));
		return signature;
	}

	private static String percentEncode(String value) throws UnsupportedEncodingException {
		// 使用URLEncoder.encode编码后，将"+","*","%7E"做替换即满足ECS API规定的编码规范
		return value != null
				? URLEncoder.encode(value, "UTF-8").replace("+", "%20").replace("*", "%2A").replace("%7E", "~") : null;
	}

	private static String calculateSignature(String key, String stringToSign) throws Exception {
		// 使用HmacSHA1算法计算HMAC值
		final String ALGORITHM = "HmacSHA1";
		Mac mac = Mac.getInstance(ALGORITHM);
		mac.init(new SecretKeySpec(key.getBytes(ENCODING), ALGORITHM));
		byte[] signData = mac.doFinal(stringToSign.getBytes(ENCODING));
		return new String(Base64.encodeBase64(signData));
	}
	private static Random random = new SecureRandom();
	/**
	 * 辅助 获取随机字符串
	 *
	 * @param length
	 * @return
	 */
	public static String getRandomString(int length) {
		StringBuffer buffer = new StringBuffer("1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
		StringBuffer sb = new StringBuffer();
		int range = buffer.length();
		for (int i = 0; i < length; i++) {
			sb.append(buffer.charAt(random.nextInt(range)));
		}
		return sb.toString();
	}

}
