package de.converter.abap.generator;

import de.converter.abap.generator.AbapAttributeGenerator;
import de.converter.abap.generator.AbapInterfaceRealizationGeneration;
import de.converter.abap.generator.AbapMethodGenerator;
import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.VisibilityKind;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Conversions;

@SuppressWarnings("all")
public class AbapClassGenerator {
  public static CharSequence getCode(final org.eclipse.uml2.uml.Class umlClass) {
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _generateClassHeader = AbapClassGenerator.generateClassHeader(umlClass);
    _builder.append(_generateClassHeader, "");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("PUBLIC SECTION.");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _generateInterfaceRealization = AbapInterfaceRealizationGeneration.generateInterfaceRealization(umlClass);
    _builder.append(_generateInterfaceRealization, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    CharSequence _generateInterfaceMethodDefinition = AbapInterfaceRealizationGeneration.generateInterfaceMethodDefinition(umlClass);
    _builder.append(_generateInterfaceMethodDefinition, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    CharSequence _generateMethods = AbapMethodGenerator.generateMethods(umlClass, VisibilityKind.PUBLIC_LITERAL);
    _builder.append(_generateMethods, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    CharSequence _generateAttributes = AbapAttributeGenerator.generateAttributes(umlClass, VisibilityKind.PUBLIC_LITERAL);
    _builder.append(_generateAttributes, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("PROTECTED SECTION.");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _generateMethods_1 = AbapMethodGenerator.generateMethods(umlClass, VisibilityKind.PROTECTED_LITERAL);
    _builder.append(_generateMethods_1, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    CharSequence _generateAttributes_1 = AbapAttributeGenerator.generateAttributes(umlClass, VisibilityKind.PROTECTED_LITERAL);
    _builder.append(_generateAttributes_1, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("PRIVATE SECTION.");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _generateMethods_2 = AbapMethodGenerator.generateMethods(umlClass, VisibilityKind.PRIVATE_LITERAL);
    _builder.append(_generateMethods_2, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    CharSequence _generateAttributes_2 = AbapAttributeGenerator.generateAttributes(umlClass, VisibilityKind.PRIVATE_LITERAL);
    _builder.append(_generateAttributes_2, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("ENDCLASS.");
    _builder.newLine();
    _builder.newLine();
    _builder.append("CLASS ");
    String _name = umlClass.getName();
    _builder.append(_name, "");
    _builder.append(" IMPLEMENTATION.");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("ENDCLASS.");
    _builder.newLine();
    return _builder;
  }
  
  public static CharSequence generateClassHeader(final org.eclipse.uml2.uml.Class umlClass) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("CLASS ");
    String _name = umlClass.getName();
    _builder.append(_name, "");
    {
      boolean _isAbstract = umlClass.isAbstract();
      if (_isAbstract) {
        _builder.append(" ABSTRACT ");
      }
    }
    {
      EList<Generalization> _generalizations = umlClass.getGeneralizations();
      int _length = ((Object[])Conversions.unwrapArray(_generalizations, Object.class)).length;
      boolean _greaterThan = (_length > 0);
      if (_greaterThan) {
        _builder.append(" INHERITING FROM ");
        EList<Generalization> _generalizations_1 = umlClass.getGeneralizations();
        Generalization _get = _generalizations_1.get(0);
        Classifier _general = _get.getGeneral();
        String _name_1 = _general.getName();
        String _string = _name_1.toString();
        _builder.append(_string, "");
      }
    }
    _builder.append(" DEFINITION.");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("PUBLIC FINAL CREATE PUBLIC.");
    _builder.newLine();
    return _builder;
  }
}
