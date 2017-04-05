package de.converter.abap.generator;

import de.converter.abap.generator.AbapMethodGenerator;
import org.eclipse.uml2.uml.Interface;
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
    CharSequence _generateMethods = AbapMethodGenerator.generateMethods(umlInterface, VisibilityKind.PUBLIC_LITERAL);
    _builder.append(_generateMethods, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("ENDINTERFACE.");
    _builder.newLine();
    return _builder;
  }
}
