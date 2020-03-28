package com.zelda.baidu;
import com.zelda.util.HttpUtil;
import com.zelda.util.PropertiesUtil;

/**
 * 获取百度的token
 * @author mantou
 */
public class AuthService {

	/**
	 * 获取API访问token
	 * 该token有一定的有效期，需要自行管理，当失效时需重新获取.
	 * @return assess_token 示例：
	 * "24.460da4889caad24cccdb1fea17221975.2592000.1491995545.282335-1234567"
	 */
	public static String getToken() {
		// 获取token地址
		String authHost = "https://aip.baidubce.com/oauth/2.0/token?";
		String getAccessTokenUrl = authHost
				// 1. grant_type为固定参数
				+ "grant_type=client_credentials"
				// 2. 官网获取的 API Key
				+ "&client_id=" + PropertiesUtil.getPropertiesValue("baidu.clientId")
				// 3. 官网获取的 Secret Key
				+ "&client_secret=" + PropertiesUtil.getPropertiesValue("baidu.clientSecret");
		String result = HttpUtil.getRequest(getAccessTokenUrl);
		return result;
	}

}