package parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class JsonElement {
	
	public JsonElement() {
		
	}
	
	// TODO: make all types constants
	public static String getElementType(String s) {
		if (s.equals("null")) {
			return "JsonNull";
		}
		if (s.equals("true") || s.equals("false")) {
			return "JsonBoolean";
		}
		if (JsonInteger.isInteger(s)) {
			return "JsonInteger";
		}
		if (JsonDouble.isDouble(s)) {
			return "JsonDouble";
		}
		if (JsonArray.isArray(s)) {
			return "JsonArray";
		}
		// TODO: add cases for JsonString and JsonObject
		return "none";
	}
	
	public static String[] tokenize(String s) {
		try {
    		return s.split("\\s+");
    	} catch (Exception e) {
	        throw e; 
	    }
	}
	
	public static JsonElement parsePrimitive(String token) {
		if (token.equals("null")) {
			return new JsonNull();
		}
		if (token.equals("true")) {
			return new JsonBoolean(true);
		}
		if (token.equals("false")) {
			return new JsonBoolean(false);
		}
		if (getElementType(token).equals("JsonInteger")) {
			return new JsonInteger(Integer.parseInt(token));
		}
		if (getElementType(token).equals("JsonDouble")) {
			return new JsonDouble(Double.parseDouble(token));
		}
		return new JsonString(token);
	}
	
	// precondition is that the entire JsonArray has been compressed into an input string (i.e. everything between [ and ])
	// TODO: (will be done prior to calling this method when iterating over tokens array)
	public static JsonElement parseArray(String arrayContents) {
		String[] listContents = arrayContents.split(",");
		List<String> arrayContentsList = Arrays.asList(listContents);
		ArrayList<JsonElement> elements = new ArrayList<JsonElement>();
		for (String c : arrayContentsList) {
			elements.add(parsePrimitive(c));
		}
		return new JsonArray(elements);
	}
	
	// precondition is that the entire JsonObject has been compressed into an input string (i.e. everything between { and })
	// TODO: (will be done prior to calling this method when iterating over tokens array)
	public static JsonElement parseObject(String objectContents) {
		String[] split = objectContents.split(",(?=[^\\]]*(?:\\[|$))");
		HashMap<String, JsonElement> map = new HashMap<String, JsonElement>();
		JsonObject jsonObj = new JsonObject(map);
		for (String i : split) {
			int colon = i.indexOf(":");
			String key = i.substring(0, colon);
			String value = i.substring(colon+1);
			String valueType = getElementType(value);
			JsonElement e = null;
			if (valueType.equals("JsonArray")) {
				int openBracket = value.indexOf("[");
				value = value.substring(openBracket+1, value.length()-1);
				e = parseArray(value);
			} else {
				e = parsePrimitive(value);
			}
			jsonObj.add(key, e);
		}
		return jsonObj;
	}
}
