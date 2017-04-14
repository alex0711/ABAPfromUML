package de.converter.abap.generator;

import de.converter.abap.generator.AbapAttributeGenerator;
import de.converter.abap.generator.AbapMethodGenerator;
import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.VisibilityKind;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class AbapInterfaceGenerator {
  public static CharSequence getCode(final Interface umlInterface) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("INTERFACE ");
    String _name = umlInterface.getName();
    String _string = _name.toString();
    _builder.append(_string, "");
    _builder.append(".");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    EList<Property> _ownedAttributes = umlInterface.getOwnedAttributes();
    CharSequence _generateAttributes = AbapAttributeGenerator.generateAttributes(_ownedAttributes, VisibilityKind.PUBLIC_LITERAL);
    String _string_1 = _generateAttributes.toString();
    String _trim = _string_1.trim();
    _builder.append(_trim, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    EList<Operation> _ownedOperations = umlInterface.getOwnedOperations();
    CharSequence _generateMethods = AbapMethodGenerator.generateMethods(_ownedOperations, VisibilityKind.PUBLIC_LITERAL);
    String _string_2 = _generateMethods.toString();
    String _trim_1 = _string_2.trim();
    _builder.append(_trim_1, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("ENDINTERFACE.");
    _builder.newLine();
    return _builder;
  }
}
