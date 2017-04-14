package de.converter.abap.generator;

import de.converter.abap.generator.AbapDocGenerator;
import de.converter.abap.generator.AbapMethodGenerator;
import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Conversions;

@SuppressWarnings("all")
public class AbapInterfaceRealizationGeneration {
  public static CharSequence generateInterfaceRealization(final org.eclipse.uml2.uml.Class umlClass) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<Interface> _directlyRealizedInterfaces = umlClass.directlyRealizedInterfaces();
      int _length = ((Object[])Conversions.unwrapArray(_directlyRealizedInterfaces, Object.class)).length;
      boolean _greaterThan = (_length > 0);
      if (_greaterThan) {
        _builder.append("INTERFACES:");
        _builder.newLine();
        _builder.append("\t");
        CharSequence _generatesingleIFName = AbapInterfaceRealizationGeneration.generatesingleIFName(umlClass);
        String _string = _generatesingleIFName.toString();
        String _trim = _string.trim();
        _builder.append(_trim, "\t");
        _builder.append(".");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  private static CharSequence generatesingleIFName(final org.eclipse.uml2.uml.Class umlClass) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<Interface> _directlyRealizedInterfaces = umlClass.directlyRealizedInterfaces();
      boolean _hasElements = false;
      for(final Interface interf : _directlyRealizedInterfaces) {
        if (!_hasElements) {
          _hasElements = true;
        } else {
          _builder.appendImmediate(",", "");
        }
        String _name = interf.getName();
        String _upperCase = _name.toUpperCase();
        _builder.append(_upperCase, "");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t");
      }
    }
    return _builder;
  }
  
  public static CharSequence generateInterfaceMethodDefinition(final org.eclipse.uml2.uml.Class umlClass) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<Interface> _directlyRealizedInterfaces = umlClass.directlyRealizedInterfaces();
      int _length = ((Object[])Conversions.unwrapArray(_directlyRealizedInterfaces, Object.class)).length;
      boolean _greaterThan = (_length > 0);
      if (_greaterThan) {
        {
          EList<Interface> _directlyRealizedInterfaces_1 = umlClass.directlyRealizedInterfaces();
          for(final Interface interf : _directlyRealizedInterfaces_1) {
            {
              EList<Operation> _ownedOperations = interf.getOwnedOperations();
              for(final Operation operation : _ownedOperations) {
                CharSequence _generateAbapDoc = AbapDocGenerator.generateAbapDoc(operation);
                _builder.append(_generateAbapDoc, "");
                _builder.newLineIfNotEmpty();
                CharSequence _generateSingleIFMethod = AbapInterfaceRealizationGeneration.generateSingleIFMethod(interf, operation);
                String _string = _generateSingleIFMethod.toString();
                String _trim = _string.trim();
                _builder.append(_trim, "");
                _builder.append(".");
              }
            }
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    return _builder;
  }
  
  private static CharSequence generateSingleIFMethod(final Interface interf, final Operation operation) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("METHODS ");
    String _name = interf.getName();
    _builder.append(_name, "");
    _builder.append("~");
    String _name_1 = operation.getName();
    _builder.append(_name_1, "");
    _builder.append(" ");
    CharSequence _generateParameters = AbapMethodGenerator.generateParameters(operation);
    _builder.append(_generateParameters, "");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
}
