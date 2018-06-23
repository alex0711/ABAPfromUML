package de.converter.abap.generator;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.uml2.uml.Classifier;

public class Generator implements ICodeGenAccess {

	private List<ICodeGenArtefact> result;

	@Override
	public List<ICodeGenArtefact> doGenerate(Resource resource) {
		result = new ArrayList<ICodeGenArtefact>();
		resource.getAllContents().forEachRemaining(uml -> parse(uml));
		return result;
	}

	private void parse(EObject uml) {
		try {
			if (uml instanceof Classifier) {
				Classifier classifier = (Classifier) uml;
				CodeGenFacade CodeGenerator = new CodeGenFacade(classifier);
				result.add(CodeGenerator.getCodeGenArtefact());
			}
		} catch (CodeGenerationException e) {
			e.printStackTrace();
		}
	}

}