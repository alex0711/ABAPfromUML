package de.converter.abap.generator;

public interface ICodeGenArtefact {

	ECodeGenTypes getType();
	String getPackage();
	String getName();
	CharSequence getCode();

}
