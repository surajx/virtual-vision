package com.pyro.imagetarget.parser;

import com.google.gson.Gson;

public class JsonParser {
	
	
	public Object parseJSON(String json,Object jsonClass)
	{
		Gson gson = new Gson();
		 return gson.fromJson(json, jsonClass.getClass());
	}

}
