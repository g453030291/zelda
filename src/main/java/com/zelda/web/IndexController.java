package com.zelda.web;

import com.zelda.entity.BaiduWords;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 首页controller
 * @author mantou
 */
@RestController
public class IndexController {

	@RequestMapping("/index.json")
	public String index(String param){
		BaiduWords baiduWords = new BaiduWords();
		baiduWords.setImage(param);
		return null;
	}
}
