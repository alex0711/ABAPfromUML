package de.converter.abap.generator;

public class CodeGenArtefact implements ICodeGenArtefact {

	private ECodeGenTypes type;
	private String name;
	private String packageName;
	private CharSequence code;

	public CodeGenArtefact(ECodeGenTypes type, String packageName, String name, CharSequence code) {
		this.name = name;
		this.type = type;
		this.code = code;
		this.packageName = packageName;
	}

	@Override
	public ECodeGenTypes getType() {
		return type;
	}

	@Override
	public String getPackage() {
		return packageName;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public CharSequence getCode() {
		return code;
	}

}