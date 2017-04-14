package de.converter.abap.generator

import org.eclipse.uml2.uml.Interface
import org.eclipse.uml2.uml.VisibilityKind

class AbapInterfaceGenerator {
	static def getCode(Interface umlInterface)'''
	INTERFACE «umlInterface.name.toString».
		«AbapAttributeGenerator.generateAttributes(umlInterface.ownedAttributes, VisibilityKind.PUBLIC_LITERAL).toString.trim»
		«AbapMethodGenerator.generateMethods(umlInterface.ownedOperations, VisibilityKind.PUBLIC_LITERAL).toString.trim»
	ENDINTERFACE.
	'''
	}