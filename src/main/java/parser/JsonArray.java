package parser;

import java.util.ArrayList;

public class JsonArray extends JsonElement {

	private ArrayList<JsonElement> arr;
	
	public JsonArray(ArrayList<JsonElement> a) {
		setJsonArray(a);
	}
	
	public ArrayList<JsonElement> getJsonArray() {
		return this.arr;
	}
	
	public void setJsonArray(ArrayList<JsonElement> a) {
		this.arr = a;
	}
	
	public void add(JsonElement element) {
		this.arr.add(element);
	}
	
	public String toString() {
		String s = "[";
		for (int i = 0; i < arr.size()-1; i++) {
			s += arr.get(i).toString() + ", ";
		}
		s += arr.get(arr.size() - 1);
		s += "]";
		return s;
	}
}
