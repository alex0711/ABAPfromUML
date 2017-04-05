
package de.converter.abap.generator;

import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Class;

public final class CodeGenFacade implements IFCodeGen {

	private Classifier classifier;
	private final String constantClass = "CLASS";

	@Override
	public CharSequence getCode() {
		String classifierType = getClassifierType(getClassifier());
		System.out.println("Classifier Type: " + classifierType);

		if (classifierType.equals(constantClass)) {
			System.out.println("Convert class");
			return AbapClassGenerator.getCode((Class) getClassifier());
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
