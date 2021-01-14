import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
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
		JsonElement n = new JsonNull();
		assertEquals(n.toString(), "null");
	}
	
	@Test
	void testJsonArrayEmpty() {
		JsonElement arr = new JsonArray(new ArrayList<JsonElement>());
		assertEquals(arr.toString(), "[]");
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
	}
	
	@Test
	void testNestedJsonArray() {
		JsonArray arr = new JsonArray(new ArrayList<JsonElement>());
		ArrayList<JsonElement> a = new ArrayList<JsonElement>();
		JsonArray arr2 = new JsonArray(a);
		a.add(new JsonInteger(1));
		arr.add(arr2);
		assertEquals("[[1]]", arr.toString());
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
		assertEquals(s, element.toString());
	}
	
	@Test
	void testParseTrue() {
		String s = "true";
		JsonElement element = JsonElement.parse(s);
		assertEquals(s, element.toString());
	}
	
	@Test
	void testParseFalse() {
		String s = "false";
		JsonElement element = JsonElement.parse(s);
		assertEquals(s, element.toString());
	}
	
	@Test
	void testParseIntPositive() {
		String s = "1273847384";
		JsonElement element = JsonElement.parse(s);
		assertEquals(s, element.toString());
	}
	
	@Test
	void testParseIntNegative() {
		String s = "-1273847384";
		JsonElement element = JsonElement.parse(s);
		assertEquals(s, element.toString());
	}
	
	@Test
	void testParseDouble() {
		String s = "1.32375873";
		JsonElement element = JsonElement.parse(s);
		assertEquals(s, element.toString());
	}
	
	@Test
	void testParseDoubleNegative() {
		String s = "-1.32375873";
		JsonElement element = JsonElement.parse(s);
		assertEquals(s, element.toString());
	}
	
	@Test
	void testString() {
		String s = "abcdefg";
		JsonElement element = JsonElement.parse(s);
		assertEquals(s, element.toString());
	}
	
	@Test
	void testStringSpace() {
		String s = "abc def ghi";
		JsonElement element = JsonElement.parse(s);
		assertEquals(s, element.toString());
	}
	
	@Test
	void testParseArray() {
		String arrayContents = "[ true ]";
		JsonElement element = JsonElement.parse(arrayContents);
		assertEquals("[true]", element.toString());
	}
	
	@Test
	void testParseArrayEmpty() {
		String arrayContents = "[ ]";
		JsonElement element = JsonElement.parse(arrayContents);
		assertEquals("[]", element.toString());
	}
	
	@Test
	void testParseArray1() {
		String arrayContents = "[ 1 ]";
		JsonElement element = JsonElement.parse(arrayContents);
		assertEquals("[1]", element.toString());
	}
	
	@Test
	void testParseArrayWrong() {
		String arrayContents = "[ , ]";
		JsonElement element = JsonElement.parse(arrayContents);
	}
	
	@Test
	void testParseArrayWrong2() {
		String arrayContents = "[ , , ]";
		JsonElement element = JsonElement.parse(arrayContents);
	}
	
	@Test
	void testParseArrayWrong3() {
		String arrayContents = "[ 1 , 2 ";
		JsonElement element = JsonElement.parse(arrayContents);
	}
	
	@Test
	void testParseArray2() {
		String arrayContents = "[ 1 , 2 ]";
		JsonElement element = JsonElement.parse(arrayContents);
		assertEquals("[1, 2]", element.toString());
	}
	
	@Test
	void testParseArray3() {
		String arrayContents = "[ 1 , 2 , 3 ]";
		JsonElement element = JsonElement.parse(arrayContents);
		assertEquals("[1, 2, 3]", element.toString());
	}
	
	@Test
	void testParseArray4() {
		String arrayContents = "[ 1 , 2 , 3 , true , false , -3.14 , [ 1 ] ]";
		JsonElement element = JsonElement.parse(arrayContents);
		assertEquals("[1, 2, 3, true, false, -3.14, [1]]", element.toString());
	}
	
	@Test
	void testParseArray5() {
		String arrayContents = "[ 1 , 2 , 3 , true , false , \"a b c\" ]";
		JsonElement element = JsonElement.parse(arrayContents);
		assertEquals("[1, 2, 3, true, false, \"a b c\"]", element.toString());
	}
	
	@Test
	void testParseArray6() {
		String arrayContents = "[ [ { \"key\" : 1.3 } ] ]";
		JsonElement element = JsonElement.parse(arrayContents);
		assertEquals("[[1]]", element.toString());
	}
	
	@Test
	void testParseArray7() {
		String arrayContents = "[ 1 , [ 1.234 ] ]";
		JsonElement element = JsonElement.parse(arrayContents);
		assertEquals("[1, [1.234]]", element.toString());
	}
	
	@Test
	void testParseArrayWithObject() {
		String arrayContents = "[ { \"a\" : 1 } ]";
		JsonElement element = JsonElement.parse(arrayContents);
		assertEquals("[{\n" + "\t" + "\"a\": 1\n}]", element.toString());
	}
	
	@Test
	void testParseObject() {
		String objectContents = "{ \"a\" : true }";
		JsonElement element = JsonElement.parse(objectContents);
		System.out.println(element.toString());
	}
	
	@Test
	void testEmptyObject() {
		String objectContents = "{ }";
		JsonElement element = JsonElement.parse(objectContents);
		assertEquals("{\n}", element.toString());
	}
	
	@Test
	void testParseObjectWrong() {
		String objectContents = "{ ";
		JsonElement element = JsonElement.parse(objectContents);
		System.out.println(element.toString());
	}
	
	@Test
	void testParseObject2() {
		String objectContents = "{ \"a\" : true , \"b\" : false }";
		JsonElement element = JsonElement.parse(objectContents);
		System.out.println(element.toString());
	}
	
	@Test
	void testParseObject3() {
		String objectContents = "{ \"a\" : true , \"b\" : 3.14 , \"c\" : [ 1 ] }";
		JsonElement element = JsonElement.parse(objectContents);
		System.out.println(element.toString());
	}
	
	@Test
	void testParseObject4() {
		String objectContents = "{ \"a\" : true , \"b\" : 3.14 , \"c\" : [ 1 , 2 , false ] }";
		JsonElement element = JsonElement.parse(objectContents);
		System.out.println(element.toString());
	}
	
	@Test
	void testParseObject5() {
		String objectContents = "{ \"a\" : true , \"b\" : 1 , \"c\" : { \"d\" : 2 , \"e\" : [ 1 , 2 ] } }";
		JsonElement element = JsonElement.parse(objectContents);
		System.out.println(element.toString());
	}
	
	@Test
	void testParseObject6() {
		String objectContents = "{ \"a\" : true , \"b\" : 1 , \"c\" : { \"d\" : 2 , \"e\" : { \"f\" : 3 } } }";
		JsonElement element = JsonElement.parse(objectContents);
		System.out.println(element.toString());
	}
}
