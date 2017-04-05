package de.converter.abap.generator;
import org.eclipse.uml2.uml.Classifier;

interface IFCodeGen {
	
	public void setClassifier(Classifier classifier);
	public CharSequence getCode();
	public String getClassifierType(Classifier classifier);

}
