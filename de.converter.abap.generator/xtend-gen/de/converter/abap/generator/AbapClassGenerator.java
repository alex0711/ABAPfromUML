package de.converter.abap.generator;

import de.converter.abap.generator.AbapAttributeGenerator;
import de.converter.abap.generator.AbapInterfaceRealizationGeneration;
import de.converter.abap.generator.AbapMethodGenerator;
import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Property;
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
    String _string = _generateInterfaceRealization.toString();
    String _trim = _string.trim();
    _builder.append(_trim, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _generateInterfaceMethodDefinition = AbapInterfaceRealizationGeneration.generateInterfaceMethodDefinition(umlClass);
    String _string_1 = _generateInterfaceMethodDefinition.toString();
    String _trim_1 = _string_1.trim();
    _builder.append(_trim_1, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    EList<Operation> _ownedOperations = umlClass.getOwnedOperations();
    CharSequence _generateMethods = AbapMethodGenerator.generateMethods(_ownedOperations, VisibilityKind.PUBLIC_LITERAL);
    String _string_2 = _generateMethods.toString();
    String _trim_2 = _string_2.trim();
    _builder.append(_trim_2, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    EList<Property> _ownedAttributes = umlClass.getOwnedAttributes();
    CharSequence _generateAttributes = AbapAttributeGenerator.generateAttributes(_ownedAttributes, VisibilityKind.PUBLIC_LITERAL);
    String _string_3 = _generateAttributes.toString();
    String _trim_3 = _string_3.trim();
    _builder.append(_trim_3, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("PROTECTED SECTION.");
    _builder.newLine();
    _builder.append("\t");
    EList<Operation> _ownedOperations_1 = umlClass.getOwnedOperations();
    CharSequence _generateMethods_1 = AbapMethodGenerator.generateMethods(_ownedOperations_1, VisibilityKind.PROTECTED_LITERAL);
    String _string_4 = _generateMethods_1.toString();
    String _trim_4 = _string_4.trim();
    _builder.append(_trim_4, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    EList<Property> _ownedAttributes_1 = umlClass.getOwnedAttributes();
    CharSequence _generateAttributes_1 = AbapAttributeGenerator.generateAttributes(_ownedAttributes_1, VisibilityKind.PROTECTED_LITERAL);
    String _string_5 = _generateAttributes_1.toString();
    String _trim_5 = _string_5.trim();
    _builder.append(_trim_5, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("PRIVATE SECTION.");
    _builder.newLine();
    _builder.append("\t");
    EList<Operation> _ownedOperations_2 = umlClass.getOwnedOperations();
    CharSequence _generateMethods_2 = AbapMethodGenerator.generateMethods(_ownedOperations_2, VisibilityKind.PRIVATE_LITERAL);
    String _string_6 = _generateMethods_2.toString();
    String _trim_6 = _string_6.trim();
    _builder.append(_trim_6, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    EList<Property> _ownedAttributes_2 = umlClass.getOwnedAttributes();
    CharSequence _generateAttributes_2 = AbapAttributeGenerator.generateAttributes(_ownedAttributes_2, VisibilityKind.PRIVATE_LITERAL);
    String _string_7 = _generateAttributes_2.toString();
    String _trim_7 = _string_7.trim();
    _builder.append(_trim_7, "\t");
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
