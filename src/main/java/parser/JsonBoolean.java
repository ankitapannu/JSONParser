package parser;

public class JsonBoolean extends JsonElement {

	private boolean bool;
	
	
	public JsonBoolean(boolean b) {
		setJsonBoolean(b);
	}
	
	public boolean getJsonBoolean() {
		return this.bool;
	}
	
	public void setJsonBoolean(boolean b) {
		this.bool = b;
	}
	
	public String toString() {
		return Boolean.toString(this.bool);
	}
}
