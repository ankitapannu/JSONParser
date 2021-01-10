package parser;

import java.util.ArrayList;
import java.util.HashMap;

public class JsonObject extends JsonElement {

	private HashMap<String, JsonElement> map;
	
	public JsonObject(HashMap<String, JsonElement> m) {
		setJsonObject(m);
	}
	
	public HashMap<String, JsonElement> getJsonObject() {
		return this.map;
	}
	
	public void setJsonObject(HashMap<String, JsonElement> m) {
		this.map = m;
	}
	
	public void add(String key, JsonElement value) {
		this.map.put(key, value);
	}
	
	public String toString() {
		String s = "{\n";
		for (String key : this.map.keySet()) {
			JsonElement value = this.map.get(key);
			s += "\t";
			s += key + ": ";
			if (value instanceof JsonObject) {
				HashMap<String, JsonElement> jsonObj = ((JsonObject) value).getJsonObject();
				s += jsonObj.toString() + "\n";	
			} else {
				s += value.toString() + "\n";
			}
		}
		s += "}";
		return s;
	}
	
	
}
