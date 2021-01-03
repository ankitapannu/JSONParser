package parser;

public class JsonString extends JsonElement {

	private String str;
	
	public JsonString(String s) {
		setJsonString(s);
	}
	
	public String getJsonString() {
		return this.str;
	}
	
	public void setJsonString(String s) {
		this.str = s;
	}
	
	public String toString() {
		return this.str;
	}
}
