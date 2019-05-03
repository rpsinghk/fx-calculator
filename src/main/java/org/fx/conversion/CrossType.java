package org.fx.conversion;

public enum CrossType {
	DIRECT("D"),
	INVERTED("Inv"),
	UNITY("1:1"),
	CROSSVIACURRENCY("NA");
	
	@SuppressWarnings("unused")
	private final String type;
	
	private CrossType(String _type){
		this.type = _type;
	}
	
	public static Object from(String _in) {
		switch(_in){
			case "D":
				return DIRECT;
			case "Inv":
				return INVERTED;
			case "1:1":
				return UNITY;
			default:
				return _in;
		}
	}
}
