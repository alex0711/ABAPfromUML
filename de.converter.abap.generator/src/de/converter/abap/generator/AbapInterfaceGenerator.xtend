package de.converter.abap.generator

import org.eclipse.uml2.uml.Interface
import org.eclipse.uml2.uml.VisibilityKind

class AbapInterfaceGenerator {
	static def getCode(Interface umlInterface)'''
	INTERFACE «umlInterface.name.toString».
		«AbapMethodGenerator.generateMethods(umlInterface, VisibilityKind.PUBLIC_LITERAL)»
	ENDINTERFACE.
	'''
	}