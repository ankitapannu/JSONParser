package parser;

public class JsonDouble extends JsonElement {

	private double num;
	
	public JsonDouble(double n) {
		setJsonDouble(n);
	}
	
	public double getJsonDouble() {
		return this.num;
	}
	
	public void setJsonDouble(double n) {
		this.num = n;
	}
	
	public String toString() {
		return Double.toString(this.num);
	}
	
	public static boolean isDouble(String s) {
	    if (s.length() == 0 || s == null) {
	        return false;
	    }
	    try {
	        double d = Double.parseDouble(s);
	    } catch (Exception e) {
	        return false;
	    }
	    return true;
	}
}
