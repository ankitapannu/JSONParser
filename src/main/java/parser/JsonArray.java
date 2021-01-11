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
		if (arr.size() > 0) {
			for (int i = 0; i < arr.size()-1; i++) {
				s += arr.get(i).toString() + ", ";
			}
			s += arr.get(arr.size() - 1);
		}
		s += "]";
		return s;
	}
	
	public static boolean isArray(String s) {
	    if (s.length() == 0 || s == null) {
	        return false;
	    }
	    if (s.charAt(0) == '[' && s.charAt(s.length()-1) == ']') {
	    	try {
	    		String arr[]= s.split(",");
	    	} catch (Exception e) {
		        return false;
		    }
		    return true;
	    } 
	    return false;
	}
}
