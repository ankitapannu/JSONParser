package parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class JsonElement {
	
	public JsonElement() {
		
	}
	
	public static String getPrimitiveElementType(String s) {
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
		return "JsonString";
	}
	
	public static JsonElement parse(String s) {	
		Tokenizer tokenizer = new Tokenizer(s);
		return parseElement(tokenizer);
	}
	
	public static JsonElement parseElement(Tokenizer t) {
		String firstToken = t.peek();
		if (firstToken.equals("{")) {
			return parseObject(t);
		}
		else if (firstToken.equals("[")) {
			return parseArray(t);
		}
		else {
			return parsePrimitive(t);
		}
	}
	
	public static JsonElement parsePrimitive(Tokenizer t) {
		String token = t.getNext();
		String primitiveType = getPrimitiveElementType(token);
		switch (primitiveType) {
			case "JsonNull":
				return new JsonNull();
			case "JsonBoolean":
				if (token.equals("true")) {
					return new JsonBoolean(true);
				} else {
					return new JsonBoolean(false);
				}
			case "JsonInteger":
				return new JsonInteger(Integer.parseInt(token));
			case "JsonDouble":
				return new JsonDouble(Double.parseDouble(token));
			case "JsonString":
				while (t.hasNext()) {
					token += " " + t.getNext();
				}
				return new JsonString(token);	
		}
		return new JsonString(token);
	}
	
	public static JsonElement parseArray(Tokenizer t) {
		String token = t.getNext();
		ArrayList<JsonElement> elements = new ArrayList<JsonElement>();
		JsonArray jsonArr = new JsonArray(elements);
		while (token != null && !token.equals("]")) {
			if (token.equals(",") && elements.size() > 0) {
				token = t.getNext();
				Tokenizer next = new Tokenizer(token);
				JsonElement e = parseElement(next);
				jsonArr.add(e);
				token = t.getNext();
			} else {
				if (t != null && t.peek() != null && t.peek().indexOf(",") != -1 && elements.size() == 0) {
					throw new IllegalArgumentException("Malformed JSON: Got , but expected JsonElement");
				}
				if (t != null && t.peek().equals("]")) {
					return jsonArr;
				}
				JsonElement e = parseElement(t);
				jsonArr.add(e);
				token = t.getNext();
			}
		}
		if (token == null) {
			throw new IllegalArgumentException("Malformed JSON: JsonArray must end with ]");
		}
		return jsonArr;
	}
	
	public static JsonElement parseObject(Tokenizer t) {
		HashMap<String, JsonElement> map = new HashMap<String, JsonElement>();
		JsonObject jsonObj = new JsonObject(map);
		String token = t.getNext();
		boolean seenColon = false;
		String key = "";
		while (token != null && !token.equals("}")) {
			if (seenColon == false) {
				key = token;
				token = t.getNext();
			}
			if (token != null && token.equals(":")) {
				seenColon = true;
				JsonElement value = parseElement(t);
				jsonObj.add(key, value);
				token = t.getNext();
				seenColon = false; // set back to false for the next (key, value) pair
			}
		}
		if (token == null) {
			throw new IllegalArgumentException("Malformed JSON: JsonObject must end with }");
		}
		return jsonObj;
	}
}
