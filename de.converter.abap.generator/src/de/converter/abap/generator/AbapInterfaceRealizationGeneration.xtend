package de.converter.abap.generator

import org.eclipse.uml2.uml.Class
import org.eclipse.uml2.uml.Interface
import org.eclipse.uml2.uml.Operation

class AbapInterfaceRealizationGeneration {
	static def generateInterfaceRealization(Class umlClass) '''
		«IF umlClass.directlyRealizedInterfaces.length > 0»
			INTERFACES:
				«generatesingleIFName(umlClass).toString.trim».
			«ENDIF»
	'''
	
	private def static CharSequence generatesingleIFName(Class umlClass)'''
		«FOR interf : umlClass.directlyRealizedInterfaces SEPARATOR ','»
			«interf.name.toUpperCase»
		«ENDFOR»'''
	

	static def generateInterfaceMethodDefinition(Class umlClass) '''
		«IF umlClass.directlyRealizedInterfaces.length > 0»
			«FOR interf : umlClass.directlyRealizedInterfaces»
				«FOR operation : interf.ownedOperations»
					«AbapDocGenerator.generateAbapDoc(operation)»
					«generateSingleIFMethod(interf, operation).toString.trim».«ENDFOR»
			«ENDFOR»
			«ENDIF»
	'''

	private def static CharSequence generateSingleIFMethod(Interface interf, Operation operation) '''
		METHODS «interf.name»~«operation.name» «AbapMethodGenerator.generateParameters(operation)»
	'''

}
