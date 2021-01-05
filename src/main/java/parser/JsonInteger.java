package parser;

public class JsonInteger extends JsonElement {
	
	private int num;
	
	public JsonInteger(int n) {
		setJsonInteger(n);
	}
	
	public int getJsonInteger() {
		return this.num;
	}
	
	public void setJsonInteger(int n) {
		this.num = n;
	}
	
	public String toString() {
		return Integer.toString(this.num);
	}
	
	public static boolean isInteger(String s) {
	    if (s.length() == 0 || s == null) {
	        return false;
	    }
	    try {
	        int i = Integer.parseInt(s);
	    } catch (Exception e) {
	        return false;
	    }
	    return true;
	}
}
