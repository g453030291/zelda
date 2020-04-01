package com.zelda.baidu;

import com.zelda.entity.BaiduWords;
import com.zelda.util.HttpUtil;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 百度文字识别
 * @author mantou
 */
public class Words {

	/**
	 * access_token
	 */
	private static String ACCESS_TOKEN = null;

	/**
	 * 通用文字识别
	 */
	private static final String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/general_basic";

	public static String commonWords(BaiduWords words){
		if(StringUtils.isEmpty(words.getImage())){
			throw new NullPointerException("image不能为空");
		}
		Map<String,String> map = new HashMap<>(7);
		map.put("image",words.getImage());
		if(!StringUtils.isEmpty(words.getUrl())){
			map.put(words.url,words.getUrl());
		}
		if(!StringUtils.isEmpty(words.getLanguage_type())){
			map.put("language_type",words.getLanguage_type());
		}
		if(!StringUtils.isEmpty(words.getDetect_direction())){
			map.put("detect_direction",words.getDetect_direction().toString());
		}
		if(!StringUtils.isEmpty(words.getDetect_language())){
			map.put("detect_language",words.getDetect_language().toString());
		}
		if(!StringUtils.isEmpty(words.getParagraph())){
			map.put("paragraph",words.getParagraph().toString());
		}
		if(!StringUtils.isEmpty(words.getProbability())){
			map.put("probability",words.getProbability().toString());
		}
		return HttpUtil.postRequest(url+"?access_token="+AuthService.getToken(),map);
	}

}
