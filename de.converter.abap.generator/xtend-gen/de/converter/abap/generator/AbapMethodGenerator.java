package de.converter.abap.generator;

import com.google.common.base.Objects;
import de.converter.abap.generator.AbapDocGenerator;
import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.VisibilityKind;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Conversions;

@SuppressWarnings("all")
public class AbapMethodGenerator {
  public static CharSequence generateMethods(final org.eclipse.uml2.uml.Class umlClass, final VisibilityKind visibility) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<Operation> _ownedOperations = umlClass.getOwnedOperations();
      for(final Operation operation : _ownedOperations) {
        {
          VisibilityKind _visibility = operation.getVisibility();
          boolean _equals = Objects.equal(_visibility, visibility);
          if (_equals) {
            CharSequence _generateAbapDoc = AbapDocGenerator.generateAbapDoc(operation);
            _builder.append(_generateAbapDoc, "");
            _builder.newLineIfNotEmpty();
            CharSequence _generateSingleMethod = AbapMethodGenerator.generateSingleMethod(operation);
            _builder.append(_generateSingleMethod, "");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    return _builder;
  }
  
  public static CharSequence generateMethods(final Interface umlInterface, final VisibilityKind visibility) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<Operation> _ownedOperations = umlInterface.getOwnedOperations();
      for(final Operation operation : _ownedOperations) {
        {
          VisibilityKind _visibility = operation.getVisibility();
          boolean _equals = Objects.equal(_visibility, visibility);
          if (_equals) {
            CharSequence _generateAbapDoc = AbapDocGenerator.generateAbapDoc(operation);
            _builder.append(_generateAbapDoc, "");
            _builder.newLineIfNotEmpty();
            CharSequence _generateSingleMethod = AbapMethodGenerator.generateSingleMethod(operation);
            _builder.append(_generateSingleMethod, "");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    return _builder;
  }
  
  public static CharSequence generateParameters(final Operation operation) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<Parameter> _inputParameters = operation.inputParameters();
      int _length = ((Object[])Conversions.unwrapArray(_inputParameters, Object.class)).length;
      boolean _greaterThan = (_length > 0);
      if (_greaterThan) {
        _builder.append("IMPORTING ");
        _builder.newLine();
        {
          EList<Parameter> _inputParameters_1 = operation.inputParameters();
          for(final Parameter input : _inputParameters_1) {
            _builder.append("\t");
            String _name = input.getName();
            _builder.append(_name, "\t");
            _builder.append(" TYPE ");
            {
              Type _type = input.getType();
              boolean _notEquals = (!Objects.equal(_type, null));
              if (_notEquals) {
                Type _type_1 = input.getType();
                String _name_1 = _type_1.getName();
                _builder.append(_name_1, "\t");
              } else {
                _builder.append("ANY");
              }
            }
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    {
      EList<Parameter> _outputParameters = operation.outputParameters();
      int _length_1 = ((Object[])Conversions.unwrapArray(_outputParameters, Object.class)).length;
      boolean _greaterThan_1 = (_length_1 > 0);
      if (_greaterThan_1) {
        _builder.append("EXPORTING ");
        _builder.newLine();
        {
          EList<Parameter> _outputParameters_1 = operation.outputParameters();
          for(final Parameter output : _outputParameters_1) {
            _builder.append("\t");
            String _name_2 = output.getName();
            _builder.append(_name_2, "\t");
            _builder.append(" TYPE ");
            {
              Type _type_2 = output.getType();
              boolean _notEquals_1 = (!Objects.equal(_type_2, null));
              if (_notEquals_1) {
                Type _type_3 = output.getType();
                String _name_3 = _type_3.getName();
                _builder.append(_name_3, "\t");
              } else {
                _builder.append("ANY");
              }
            }
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    return _builder;
  }
  
  private static CharSequence generateSingleMethod(final Operation operation) {
    StringConcatenation _builder = new StringConcatenation();
    {
      boolean _isStatic = operation.isStatic();
      if (_isStatic) {
        _builder.append("CLASS-");
      }
    }
    _builder.append("METHODS ");
    String _name = operation.getName();
    _builder.append(_name, "");
    _builder.newLineIfNotEmpty();
    CharSequence _generateParameters = AbapMethodGenerator.generateParameters(operation);
    _builder.append(_generateParameters, "");
    _builder.append(".");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
}
