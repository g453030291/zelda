package com.zelda;

import com.zelda.util.HttpUtil;
import com.zelda.util.PropertiesUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ZeldaApplicationTests {

	@Test
	public void contextLoads() throws IOException {
		String resource = PropertiesUtil.getPropertiesValue("baidu.clientId");
		System.out.println(resource);
	}

}
