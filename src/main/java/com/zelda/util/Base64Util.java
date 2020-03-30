package com.zelda.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;

/**
 * base64工具类
 * @author mantou
 */
public class Base64Util {

	private static final Base64.Decoder DECODER = Base64.getDecoder();

	private static final Base64.Encoder ENCODER = Base64.getEncoder();

	/**
	 * final Base64.Decoder decoder = Base64.getDecoder();
	 * final Base64.Encoder encoder = Base64.getEncoder();
	 * final String text = "Java深入";
	 * final byte[] textByte = text.getBytes("UTF-8");
	 * //编码
	 * final String encodedText = encoder.encodeToString(textByte);
	 * System.out.println(encodedText);
	 * //解码
	 * System.out.println(new String(decoder.decode(encodedText), "UTF-8"));
	 * finalBase64.Decoder decoder = Base64.getDecoder();
	 * final Base64.Encoder encoder = Base64.getEncoder();
	 * final String text = "Java深入";
	 * final byte[] textByte = text.getBytes("UTF-8");
	 * //编码
	 * final String encodedText = encoder.encodeToString(textByte);
	 * System.out.println(encodedText);
	 * //解码
	 * System.out.println(new String(decoder.decode(encodedText), "UTF-8"));
	 */

	public static String encodeBase64File(File file){
		try(FileInputStream inputStream = new FileInputStream(file)){
			byte[] buffer = new byte[(int)file.length()];
			inputStream.read(buffer);
			return ENCODER.encodeToString(buffer);
		}catch (IOException e){
			e.printStackTrace();
		}
		return null;
	}

}
