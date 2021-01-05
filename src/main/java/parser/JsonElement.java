package parser;

import java.util.ArrayList;
import java.util.Arrays;
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
	public static JsonElement parse(String s) {
		String elementType = getElementType(s);
		switch (elementType) {
			case "JsonNull":
				return new JsonNull();
			case "JsonBoolean":
				if (s.equals("true")) {
					return new JsonBoolean(true);
				}
				return new JsonBoolean(false);
			case "JsonInteger":
				return new JsonInteger(Integer.parseInt(s));
			case "JsonDouble":
				return new JsonDouble(Double.parseDouble(s));
			default:
				return new JsonString(s);
		}		
	}
}
