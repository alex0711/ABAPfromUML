package de.converter.abap.generator
import org.eclipse.uml2.uml.Class
import org.eclipse.uml2.uml.VisibilityKind


class AbapClassGenerator {
	
 	static def getCode(Class umlClass)'''
	«generateClassHeader(umlClass)»
		PUBLIC SECTION.
		«AbapInterfaceRealizationGeneration.generateInterfaceRealization(umlClass).toString.trim»
		
		«AbapInterfaceRealizationGeneration.generateInterfaceMethodDefinition(umlClass).toString.trim»
		«AbapMethodGenerator.generateMethods(umlClass.ownedOperations, VisibilityKind.PUBLIC_LITERAL).toString.trim»
		
		«AbapAttributeGenerator.generateAttributes(umlClass.ownedAttributes, VisibilityKind.PUBLIC_LITERAL).toString.trim»
		
		PROTECTED SECTION.
		«AbapMethodGenerator.generateMethods(umlClass.ownedOperations, VisibilityKind.PROTECTED_LITERAL).toString.trim»
		
		«AbapAttributeGenerator.generateAttributes(umlClass.ownedAttributes, VisibilityKind.PROTECTED_LITERAL).toString.trim»
		
		PRIVATE SECTION.
		«AbapMethodGenerator.generateMethods(umlClass.ownedOperations, VisibilityKind.PRIVATE_LITERAL).toString.trim»
		
		«AbapAttributeGenerator.generateAttributes(umlClass.ownedAttributes, VisibilityKind.PRIVATE_LITERAL).toString.trim»
	ENDCLASS.
	
	CLASS «umlClass.name» IMPLEMENTATION.
	
	ENDCLASS.
	'''
	
	static def generateClassHeader(Class umlClass)'''
	CLASS «umlClass.name»«IF umlClass.isAbstract» ABSTRACT «ENDIF»«IF umlClass.generalizations.length > 0» INHERITING FROM «umlClass.generalizations.get(0).general.name.toString»«ENDIF» DEFINITION.
		PUBLIC FINAL CREATE PUBLIC.
	'''
}