
package de.converter.abap.generator;

import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Class;

public final class CodeGenFacade implements IFCodeGen {

	private Classifier classifier;
	private final String constantClass = "CLASS";
	private final String constantInterface = "INTERFACE";

	@Override
	public CharSequence getCode() {
		String classifierType = getClassifierType(getClassifier());
		System.out.println("Classifier Type: " + classifierType);

		if (classifierType.equals(constantClass)) {
			System.out.println("Convert Class");
			return AbapClassGenerator.getCode((Class) getClassifier());
		} else if (classifierType.equals(constantInterface)) {
			System.out.println("Convert Interface");
			return AbapInterfaceGenerator.getCode((Interface) getClassifier());
		} else {
			return null;
		}
	}

	@Override
	public String getClassifierType(Classifier classifier) {
		return classifier.getClass().getSimpleName().toString().toUpperCase().replace("IMPL", "");
	}

	private Classifier getClassifier() {
		return classifier;
	}

	@Override
	public void setClassifier(Classifier classifier) {
		this.classifier = classifier;
	}
}
