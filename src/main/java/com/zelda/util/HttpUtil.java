package com.zelda.util;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * http工具类
 * @author mantou
 */
@Slf4j
public class HttpUtil {

	/**
	 * json格式
	 */
	private static final MediaType JSON = MediaType.get("application/json;charset=utf-8");

	/**
	 * 表单格式
	 */
	private static final MediaType FORM = MediaType.get("application/x-www-form-urlencoded");

	private static final OkHttpClient CLIENT = new OkHttpClient.Builder()
			.connectTimeout(1000, TimeUnit.MILLISECONDS)
			.readTimeout(1000,TimeUnit.MILLISECONDS)
			.build();

	/**
	 * post请求,表单提交
	 * @param url
	 * @param map
	 * @return
	 */
	public static String postRequest(String url, Map<String,String> map) throws IOException {
		if(StringUtils.isEmpty(url) || map == null || map.isEmpty()){
			throw new NullPointerException("url 或 map 为空");
		}
		FormBody.Builder builder = new FormBody.Builder();
		for (Map.Entry<String,String> entry : map.entrySet()){
			builder.add(entry.getKey(),entry.getValue());
		}
		RequestBody requestBody = builder.build();
		Request request = new Request.Builder()
				.url(url)
				.post(requestBody)
				.build();
		try (Response response = CLIENT.newCall(request).execute()){
			return response.body().string();
		}catch (IOException e){
			log.debug("http post fail");
			throw new IOException(e.getMessage(),e.fillInStackTrace());
		}
	}

}
