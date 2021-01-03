package parser;

public class JsonNull extends JsonElement {

	private String str;
	
	public JsonNull() {
		this.str = "null";
	}
	
	public String getJsonNull() {
		return this.str;
	}
	
	public String toString() {
		return this.str;
	}
}
