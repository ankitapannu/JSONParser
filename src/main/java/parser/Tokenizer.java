package parser;

public class Tokenizer {
	
	public int index;
	public String[] tokens;
	
	public Tokenizer(String s) {
		index = 0;
		tokens = s.split("\\s+");
	}
	
	public String getNext() {
		if (index < tokens.length) {
			String next = tokens[index];
			index++;
			return next;
		} else {
			return null;
		}
		
	}
	
	public int getIndex() {
		return index;
		
	}
	
	public boolean hasNext() {
		return index < tokens.length;
	}
	
	public String peek() {
		if (index < tokens.length) {
			return tokens[index];
		} else {
			return null;
		}
	}

}

