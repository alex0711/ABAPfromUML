package de.converter.abap.generator

import org.eclipse.emf.common.util.EList
import org.eclipse.uml2.uml.Operation
import org.eclipse.uml2.uml.VisibilityKind
import org.eclipse.uml2.uml.ParameterDirectionKind

class AbapMethodGenerator {
	static def generateMethodsImplementation(EList<Operation> OperationList) '''
		«FOR operation : OperationList»
			METHOD «operation.name.toLowerCase».
			
			ENDMETHOD.
		«ENDFOR»
	'''
	
	static def generateMethods(EList<Operation> OperationList, VisibilityKind visibility) '''
		«FOR operation : OperationList»
			«IF operation.visibility == visibility»
				«AbapDocGenerator.generateAbapDoc(operation)»
				«generateSingleMethod(operation).toString.trim».
			«ENDIF»
		«ENDFOR»
	'''

	static def generateParameters(Operation operation) '''
		«FOR input : operation.inputParameters.filter[direction.equals(ParameterDirectionKind.IN_LITERAL)] BEFORE "IMPORTING "»
			«input.name.toLowerCase» TYPE «IF input.type != null»«input.type.name.toLowerCase»«ELSE»ANY«ENDIF»
		«ENDFOR»
		«FOR output : operation.outputParameters.filter[direction.equals(ParameterDirectionKind.OUT_LITERAL)] BEFORE "EXPORTING "»
			«output.name.toLowerCase» TYPE «IF output.type != null»«output.type.name.toLowerCase»«ELSE»ANY«ENDIF»
		«ENDFOR»
		«FOR changing : operation.inputParameters.filter[direction.equals(ParameterDirectionKind.INOUT_LITERAL)] BEFORE "CHANGING "»
			«changing.name.toLowerCase» TYPE «IF changing.type != null»«changing.type.name.toLowerCase»«ELSE»ANY«ENDIF»
		«ENDFOR»
		«FOR returning : operation.outputParameters.filter[direction.equals(ParameterDirectionKind.RETURN_LITERAL)] BEFORE "RETURNING VALUE"»
			(«returning.name.toLowerCase») TYPE «IF returning.type != null»«returning.type.name.toLowerCase»«ELSE»ANY«ENDIF»
		«ENDFOR»
	'''

	private static def generateSingleMethod(Operation operation) '''
		«IF operation.isStatic»CLASS-«ENDIF»METHODS «operation.name.toLowerCase»
			«generateParameters(operation).toString.trim»
	'''
}