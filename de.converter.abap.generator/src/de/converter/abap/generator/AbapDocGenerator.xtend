package de.converter.abap.generator

import org.eclipse.uml2.uml.Property
import org.eclipse.uml2.uml.Operation

class AbapDocGenerator {

	static def generateAbapDoc(Property property) '''
		"! «property.visibility» «IF property.isStatic»Static «ELSE»Instance «ENDIF»Attribute «property.name»
		«FOR comment : property.ownedComments»
		"! «comment.body.toString»
		«ENDFOR»
	'''

	static def generateAbapDoc(Operation operation) '''
		"! «operation.visibility» «IF operation.isStatic»Static «ELSE»Instance «ENDIF»Method «operation.name»
		«FOR comment : operation.ownedComments»
			"! «comment.body.toString»
		«ENDFOR»
		«FOR input : operation.inputParameters»
			"! @parameter «input.name» | TYPE «IF input.type != null»«input.type.name»«ENDIF»
		«ENDFOR»
		«FOR output : operation.outputParameters»
			"! @parameter «output.name» | TYPE «IF output.type != null»«output.type.name»«ENDIF»
		«ENDFOR»
		«FOR raising : operation.raisedExceptions»
			"! @raising «raising.name» |
		«ENDFOR»
	'''
}
