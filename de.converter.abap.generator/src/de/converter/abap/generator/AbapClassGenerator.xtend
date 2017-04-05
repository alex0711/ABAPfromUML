package de.converter.abap.generator
import org.eclipse.uml2.uml.Class
import org.eclipse.uml2.uml.VisibilityKind


class AbapClassGenerator {
	
 	static def getCode(Class umlClass)'''
	«generateClassHeader(umlClass)»
		PUBLIC SECTION.
		«AbapInterfaceRealizationGeneration.generateInterfaceRealization(umlClass)»
		«AbapInterfaceRealizationGeneration.generateInterfaceMethodDefinition(umlClass)»
		«AbapMethodGenerator.generateMethods(umlClass, VisibilityKind.PUBLIC_LITERAL)»
		«AbapAttributeGenerator.generateAttributes(umlClass, VisibilityKind.PUBLIC_LITERAL)»
		
		PROTECTED SECTION.
		«AbapMethodGenerator.generateMethods(umlClass, VisibilityKind.PROTECTED_LITERAL)»
		«AbapAttributeGenerator.generateAttributes(umlClass, VisibilityKind.PROTECTED_LITERAL)»
		
		PRIVATE SECTION.
		«AbapMethodGenerator.generateMethods(umlClass, VisibilityKind.PRIVATE_LITERAL)»
		«AbapAttributeGenerator.generateAttributes(umlClass, VisibilityKind.PRIVATE_LITERAL)»
	ENDCLASS.
	
	CLASS «umlClass.name» IMPLEMENTATION.
	
	ENDCLASS.
	'''
	
	static def generateClassHeader(Class umlClass)'''
	CLASS «umlClass.name»«IF umlClass.isAbstract» ABSTRACT «ENDIF»«IF umlClass.generalizations.length > 0» INHERITING FROM «umlClass.generalizations.get(0).general.name.toString»«ENDIF» DEFINITION.
		PUBLIC FINAL CREATE PUBLIC.
	'''
}