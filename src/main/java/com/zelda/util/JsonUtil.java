package com.zelda.util;

import com.google.gson.*;

import java.io.Reader;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * json工具类
 * @author mantou
 */
public class JsonUtil {

	private static final Gson gson = new GsonBuilder()
													.excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
													.serializeNulls()
													.setLongSerializationPolicy(LongSerializationPolicy.STRING)
													.setDateFormat("yyyy-MM-dd HH:mm:ss")
													.disableHtmlEscaping()
													.create();

	private static final JsonParser jsonParser = new JsonParser();

	private JsonUtil() {

	}

	/**
	 * 获取一个gson实例对象
	 * @return
	 */
	public static Gson getGsonInstance() {
		return gson;
	}

	/**
	 * Object2JsonString
	 * @param object
	 * @return
	 */
	public static String toJsonString(Object object) {
		return gson.toJson(object);
	}

	public static String toJsonString(Object object,Type type) {
		return gson.toJson(object, type);
	}

	/**
	 * JsonString2Object
	 * @param json
	 * @return
	 */
	public static Object fromJsonString(String json) {
		return gson.fromJson(json, Object.class);
	}

	public static Object fromJsonString(String json,Type type) {
		return gson.fromJson(json, type);
	}

	public static Object fromJsonString(Reader reader,Type type){
		return gson.fromJson(reader,type);
	}

	/**
	 * String2JsonObject
	 * @param str
	 * @return
	 */
	public static JsonObject toJsonObject(String str) {
		return jsonParser.parse(str).getAsJsonObject();
	}

	/**
	 * String2JsonArray
	 * @param str
	 * @return
	 */
	public static JsonArray toJsonArray(String str) {
		return jsonParser.parse(str).getAsJsonArray();
	}

	/**
	 * JsonString2List
	 * @param json
	 * @param clazz
	 * @param <T>
	 * @return
	 */
	public static <T> List<T> toList(String json,Class clazz){
		Type type = new ParameterizedTypeImpl(clazz);
		List<T> list = gson.fromJson(json,type);
		return list;
	}

	/**
	 *  判断string是否为一个json对象
	 * @param str
	 * @return
	 */
	public static boolean isJsonObject(String str) {
		try {
			return toJsonObject(str).isJsonObject();
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 *  判断string是否为一个json数组
	 * @param str
	 * @return
	 */
	public static boolean isJsonArray(String str) {
		try {
			return toJsonArray(str).isJsonArray();
		} catch (Exception e) {
			return false;
		}
	}

	private static class ParameterizedTypeImpl implements ParameterizedType {
		Class clazz;

		public ParameterizedTypeImpl(Class clz){
			clazz = clz;
		}

		@Override
		public Type[] getActualTypeArguments() {
			return new Type[]{clazz};
		}

		@Override
		public Type getRawType() {
			return List.class;
		}

		@Override
		public Type getOwnerType() {
			return null;
		}
	}
}
