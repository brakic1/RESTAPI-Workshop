package org.workshop.api.utilities;

import com.google.gson.Gson;

public class JsonUtility {

	/**
	 * Java object to JSON string
	 * 
	 * @param obj
	 * @return Json String
	 */
	public static String POJOToJson(Object obj) {
		Gson gson = new Gson();
		return gson.toJson(obj);
	}
}
