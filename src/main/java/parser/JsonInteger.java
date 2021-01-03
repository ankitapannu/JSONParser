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
}
