package de.converter.abap.generator;

public enum ECodeGenTypes {

	CLASS("CLAS"), INTERFACE("INTF"), PACKAGE("PCKG");

	private final String value;

	private ECodeGenTypes(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}