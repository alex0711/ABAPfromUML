package de.converter.abap.generator;

import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Class;

public class CodeGenFacade {

	private Classifier classifier;

	public ICodeGenArtefact getCodeGenArtefact() throws CodeGenerationException {
		CharSequence code = "";
		ECodeGenTypes type = null;
		String packageName = classifier.getPackage().getName();
		String name = classifier.getName();

		if (classifier instanceof Class) {
			code = AbapClassGenerator.getCode((Class) classifier);
			type = ECodeGenTypes.CLASS;
		} else if (classifier instanceof Interface) {
			code = AbapInterfaceGenerator.getCode((Interface) classifier);
			type = ECodeGenTypes.INTERFACE;
		} else {
			throw new CodeGenerationException("Unsupported Type");
		}
		return new CodeGenArtefact(type, packageName, name, code);
	}

	public CodeGenFacade(Classifier classifier) {
		this.classifier = classifier;
	}
}
