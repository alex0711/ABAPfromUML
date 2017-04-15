package de.converter.abap.generator

import org.eclipse.uml2.uml.Class
import org.eclipse.uml2.uml.Interface
import org.eclipse.uml2.uml.Operation
import org.eclipse.uml2.uml.Property

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

	static def generateInterfaceAttributeDefinition(Class umlClass) '''
		«IF umlClass.directlyRealizedInterfaces.length > 0»
			«FOR interf : umlClass.directlyRealizedInterfaces»
				«FOR property : interf.ownedAttributes»
					«AbapDocGenerator.generateAbapDoc(property)»
					«generateSingleIFAttribute(interf, property).toString.trim»
				«ENDFOR»
			«ENDFOR»
			«ENDIF»
	'''
	
	def static generateSingleIFAttribute(Interface interf, Property property)'''
		«IF property.isStatic»CLASS-«ENDIF»DATA «interf.name»~«property.name» TYPE «IF property.type != null»«property.type.name»«ELSE»ANY«ENDIF».
	'''
	

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
