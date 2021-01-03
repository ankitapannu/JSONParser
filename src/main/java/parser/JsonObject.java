package parser;

import java.util.ArrayList;
import java.util.HashMap;

public class JsonObject extends JsonElement {

	private HashMap<String, JsonElement> map;
	private int index;
	
	public JsonObject(HashMap<String, JsonElement> m) {
		setJsonObject(m);
		index = 0;
	}
	
	public HashMap<String, JsonElement> getJsonObject() {
		return this.map;
	}
	
	public void setJsonObject(HashMap<String, JsonElement> m) {
		this.map = m;
	}
	
	public void add(String key, JsonElement value) {
		ArrayList<JsonElement> arr = new ArrayList<JsonElement>();
		arr.add(value);
		arr.add(new JsonInteger(index));
		JsonArray a = new JsonArray(arr);
		this.map.put(key, a);
		index++;
	}
	
	public String toString() {
		String s = "{\n";
		for (String key : this.map.keySet()) {
			JsonArray value = (JsonArray) this.map.get(key);
			JsonElement element = value.getJsonArray().get(0);
			s += "\t";
			s += key + ": " + element.toString() + "\n";
		}
		s += "}";
		return s;
	}
	
	
}
