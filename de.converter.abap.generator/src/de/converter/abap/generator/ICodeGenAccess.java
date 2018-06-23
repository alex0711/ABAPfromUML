package de.converter.abap.generator;

import java.util.List;
import org.eclipse.emf.ecore.resource.Resource;

interface ICodeGenAccess {

	public List<ICodeGenArtefact> doGenerate(Resource resource);
	
}