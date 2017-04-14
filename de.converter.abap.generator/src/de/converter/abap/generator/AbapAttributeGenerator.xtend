package de.converter.abap.generator

import org.eclipse.uml2.uml.VisibilityKind
import org.eclipse.uml2.uml.Property
import org.eclipse.emf.common.util.EList

class AbapAttributeGenerator {
	static def generateAttributes(EList<Property> AttributeList, VisibilityKind Visibility) '''
		«FOR attribute : AttributeList»
			«IF attribute.visibility == Visibility»
				«generateSingleAttribute(attribute)»
			«ENDIF»
		«ENDFOR»
	'''

	static def generateSingleAttribute(Property property) '''
		«AbapDocGenerator.generateAbapDoc(property)»
		«IF property.isStatic»CLASS-«ENDIF»DATA «property.name» TYPE «IF property.type != null»«property.type.name»«ELSE»ANY«ENDIF».
	'''
}
