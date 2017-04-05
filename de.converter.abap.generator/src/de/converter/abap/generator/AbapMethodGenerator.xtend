package de.converter.abap.generator

import org.eclipse.uml2.uml.Class
import org.eclipse.uml2.uml.Operation
import org.eclipse.uml2.uml.VisibilityKind

class AbapMethodGenerator {
	static def generateMethods(Class umlClass, VisibilityKind visibility) '''
		«FOR operation : umlClass.ownedOperations»
			«IF operation.visibility == visibility»
				«AbapDocGenerator.generateAbapDoc(operation)»
				«generateSingleMethod(operation)»
			«ENDIF»
		«ENDFOR»
		
	'''
	
	static def generateParameters(Operation operation)'''
		«IF operation.inputParameters.length > 0»
				IMPORTING 
					«FOR input : operation.inputParameters»
					«input.name» TYPE «IF input.type != null»«input.type.name»«ELSE»ANY«ENDIF»
					«ENDFOR»
		«ENDIF»
		«IF operation.outputParameters.length > 0»
				EXPORTING 
					«FOR output : operation.outputParameters»
					«output.name» TYPE «IF output.type != null»«output.type.name»«ELSE»ANY«ENDIF»
					«ENDFOR»	
		«ENDIF»
	'''

	private static def generateSingleMethod(Operation operation) '''
		«IF operation.isStatic»CLASS-«ENDIF»METHODS «operation.name»
		«generateParameters(operation)».
	'''
}
