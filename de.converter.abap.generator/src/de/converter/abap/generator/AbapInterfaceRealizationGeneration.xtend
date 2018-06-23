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

	private def static CharSequence generatesingleIFName(Class umlClass) '''
	«FOR interf : umlClass.directlyRealizedInterfaces SEPARATOR ','»
		«interf.name.toLowerCase»
	«ENDFOR»'''

	static def generateInterfaceAttributeDefinition(Class umlClass) '''
		«IF umlClass.directlyRealizedInterfaces.length > 0»
			«FOR interf : umlClass.directlyRealizedInterfaces»
				«FOR property : interf.ownedAttributes»
					«AbapDocGenerator.generateAbapDoc(property)»
					«generateSingleIfAttribute(interf, property).toString.trim»
				«ENDFOR»
			«ENDFOR»
		«ENDIF»
	'''

	def static generateSingleIfAttribute(Interface interf,
		Property property) '''
		«IF property.isStatic»CLASS-«ENDIF»DATA «interf.name.toLowerCase»~«property.name.toLowerCase» TYPE «IF property.type != null»«property.type.name.toLowerCase»«ELSE»ANY«ENDIF».
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

	private def static CharSequence generateSingleIFMethod(Interface interf,
		Operation operation) '''
		METHODS «interf.name.toLowerCase»~«operation.name.toLowerCase»
			«AbapMethodGenerator.generateParameters(operation)»
	'''

	static def generateInterfaceMethodImplementation(Class umlClass) '''
		«IF umlClass.directlyRealizedInterfaces.length > 0»
			«FOR interf : umlClass.directlyRealizedInterfaces»
				«FOR operation : interf.ownedOperations»
				«generateSingleIFMethodImplementation(interf, operation).toString.trim»«ENDFOR»
			«ENDFOR»
			«ENDIF»
	'''

	private def static CharSequence generateSingleIFMethodImplementation(Interface interf, Operation operation) '''
		METHOD «interf.name.toLowerCase»~«operation.name.toLowerCase».
		
		ENDMETHOD.
	'''

}
