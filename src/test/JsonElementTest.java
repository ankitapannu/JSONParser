import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;

import parser.*;

import org.junit.jupiter.api.Test;

class JsonElementTest {

	@Test
	void testJsonString() {
		JsonString s = new JsonString("abc");
		assertEquals(s.getJsonString(), "abc");
	}
	
	@Test
	void testJsonBoolean() {
		JsonBoolean t = new JsonBoolean(true);
		JsonBoolean f = new JsonBoolean(false);
		assertEquals(t.getJsonBoolean(), true);
		assertEquals(f.getJsonBoolean(), false);
	}

	@Test
	void testJsonDouble() {
		double n = 2.0e-15;
		JsonDouble num = new JsonDouble(n);
		assertEquals(num.getJsonDouble(), n);
	}
	
	@Test
	void testJsonNull() {
		JsonNull n = new JsonNull();
		assertEquals(n.toString(), "null");
	}
	
	@Test
	void testJsonArray() {
		JsonArray arr = new JsonArray(new ArrayList<JsonElement>());
		arr.add(new JsonString("abc"));
		arr.add(new JsonString("def"));
		arr.add(new JsonBoolean(true));
		arr.add(new JsonBoolean(false));
		arr.add(new JsonInteger(3));
		assertEquals(arr.getJsonArray().get(0).toString(), "abc");
		assertEquals(arr.getJsonArray().get(1).toString(), "def");
		assertEquals(arr.getJsonArray().get(2).toString(), "true");
		assertEquals(arr.getJsonArray().get(3).toString(), "false");
		assertEquals(arr.getJsonArray().get(4).toString(), "3");
		System.out.println(arr.toString());
	}
	
	@Test
	void testJsonObject() {
		HashMap<String, JsonElement> map = new HashMap<String, JsonElement>();
		JsonObject jsonObj = new JsonObject(map);
		JsonArray arr = new JsonArray(new ArrayList<JsonElement>());
		arr.add(new JsonString("abc"));
		arr.add(new JsonString("def"));
		arr.add(new JsonBoolean(true));
		arr.add(new JsonBoolean(false));
		arr.add(new JsonInteger(3));
		jsonObj.add("a", new JsonInteger(1));
		jsonObj.add("b", new JsonInteger(2));
		jsonObj.add("c", arr);
		HashMap<String, JsonElement> map2 = new HashMap<String, JsonElement>();
		System.out.println(jsonObj.toString());
	}
	
	@Test
	void testParseNull() {
		String s = "null";
		JsonElement element = JsonElement.parse(s);
		assertEquals(element.toString(), s);
	}
	
	@Test
	void testParseTrue() {
		String s = "true";
		JsonElement element = JsonElement.parse(s);
		assertEquals(element.toString(), s);
	}
	
	@Test
	void testParseFalse() {
		String s = "false";
		JsonElement element = JsonElement.parse(s);
		assertEquals(element.toString(), s);
	}
	
	@Test
	void testParseIntPositive() {
		String s = "1273847384";
		JsonElement element = JsonElement.parse(s);
		assertEquals(element.toString(), s);
	}
	
	@Test
	void testParseIntNegative() {
		String s = "-1273847384";
		JsonElement element = JsonElement.parse(s);
		assertEquals(element.toString(), s);
	}
	
	@Test
	void testParseDouble1() {
		String s = "1.32375873";
		JsonElement element = JsonElement.parse(s);
		assertEquals(element.toString(), s);
	}
	
	@Test
	void testParseDouble2() {
		String s = "-1.32375873";
		JsonElement element = JsonElement.parse(s);
		assertEquals(element.toString(), s);
	}
	
	@Test
	void testString() {
		String s = "abcdefg";
		JsonElement element = JsonElement.parse(s);
		assertEquals(element.toString(), s);
	}
}
