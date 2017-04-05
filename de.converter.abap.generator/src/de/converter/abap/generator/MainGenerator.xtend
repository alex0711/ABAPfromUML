package de.converter.abap.generator

import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.uml2.uml.Classifier
import org.eclipse.xtext.generator.IFileSystemAccess
import org.eclipse.xtext.generator.IGenerator

class MainGenerator implements IGenerator {
	
	private CodeGenFacade CodeGenerator = new CodeGenFacade();

	override doGenerate(Resource resource, IFileSystemAccess fsa) {
		for (classifier : resource.allContents.toIterable.filter(typeof(Classifier))) {
			System.out.println("Classifier found: " + classifier.name.toString);
			CodeGenerator.classifier = classifier;
			val generatedCode = CodeGenerator.code;
			if (generatedCode != null) {
				fsa.generateFile(classifier.name + ".txt", generatedCode);
				System.out.println("Generated: " + generatedCode);
			}
		}
	}
}
