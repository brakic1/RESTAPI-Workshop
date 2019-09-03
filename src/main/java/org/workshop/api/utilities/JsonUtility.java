package org.workshop.api.utilities;

import com.google.gson.Gson;

import groovy.json.StringEscapeUtils;

public class JsonUtility {
	
	/**
	 * Java object to JSON string
	 * @param obj
	 * @return Json String
	 * @throws JsonProcessingException 
	 */
 public static String POJOToJson (Object obj) {
	 Gson gson = new Gson();
	 String json =  gson.toJson(obj);
	 return StringEscapeUtils.unescapeJava(json);
 }
}
