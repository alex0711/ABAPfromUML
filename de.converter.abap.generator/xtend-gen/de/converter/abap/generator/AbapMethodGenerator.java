package de.converter.abap.generator;

import com.google.common.base.Objects;
import de.converter.abap.generator.AbapDocGenerator;
import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterDirectionKind;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.VisibilityKind;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

@SuppressWarnings("all")
public class AbapMethodGenerator {
  public static CharSequence generateMethods(final EList<Operation> OperationList, final VisibilityKind visibility) {
    StringConcatenation _builder = new StringConcatenation();
    {
      for(final Operation operation : OperationList) {
        {
          VisibilityKind _visibility = operation.getVisibility();
          boolean _equals = Objects.equal(_visibility, visibility);
          if (_equals) {
            CharSequence _generateAbapDoc = AbapDocGenerator.generateAbapDoc(operation);
            _builder.append(_generateAbapDoc, "");
            _builder.newLineIfNotEmpty();
            CharSequence _generateSingleMethod = AbapMethodGenerator.generateSingleMethod(operation);
            String _string = _generateSingleMethod.toString();
            String _trim = _string.trim();
            _builder.append(_trim, "");
            _builder.append(".");
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
      final Function1<Parameter, Boolean> _function = (Parameter it) -> {
        ParameterDirectionKind _direction = it.getDirection();
        return Boolean.valueOf(_direction.equals(ParameterDirectionKind.IN_LITERAL));
      };
      Iterable<Parameter> _filter = IterableExtensions.<Parameter>filter(_inputParameters, _function);
      boolean _hasElements = false;
      for(final Parameter input : _filter) {
        if (!_hasElements) {
          _hasElements = true;
          _builder.append("IMPORTING ", "");
        }
        String _name = input.getName();
        _builder.append(_name, "");
        _builder.append(" TYPE ");
        {
          Type _type = input.getType();
          boolean _notEquals = (!Objects.equal(_type, null));
          if (_notEquals) {
            Type _type_1 = input.getType();
            String _name_1 = _type_1.getName();
            _builder.append(_name_1, "");
          } else {
            _builder.append("ANY");
          }
        }
        _builder.newLineIfNotEmpty();
      }
    }
    {
      EList<Parameter> _outputParameters = operation.outputParameters();
      final Function1<Parameter, Boolean> _function_1 = (Parameter it) -> {
        ParameterDirectionKind _direction = it.getDirection();
        return Boolean.valueOf(_direction.equals(ParameterDirectionKind.OUT_LITERAL));
      };
      Iterable<Parameter> _filter_1 = IterableExtensions.<Parameter>filter(_outputParameters, _function_1);
      boolean _hasElements_1 = false;
      for(final Parameter output : _filter_1) {
        if (!_hasElements_1) {
          _hasElements_1 = true;
          _builder.append("EXPORTING ", "");
        }
        String _name_2 = output.getName();
        _builder.append(_name_2, "");
        _builder.append(" TYPE ");
        {
          Type _type_2 = output.getType();
          boolean _notEquals_1 = (!Objects.equal(_type_2, null));
          if (_notEquals_1) {
            Type _type_3 = output.getType();
            String _name_3 = _type_3.getName();
            _builder.append(_name_3, "");
          } else {
            _builder.append("ANY");
          }
        }
        _builder.newLineIfNotEmpty();
      }
    }
    {
      EList<Parameter> _inputParameters_1 = operation.inputParameters();
      final Function1<Parameter, Boolean> _function_2 = (Parameter it) -> {
        ParameterDirectionKind _direction = it.getDirection();
        return Boolean.valueOf(_direction.equals(ParameterDirectionKind.INOUT_LITERAL));
      };
      Iterable<Parameter> _filter_2 = IterableExtensions.<Parameter>filter(_inputParameters_1, _function_2);
      boolean _hasElements_2 = false;
      for(final Parameter changing : _filter_2) {
        if (!_hasElements_2) {
          _hasElements_2 = true;
          _builder.append("CHANGING ", "");
        }
        String _name_4 = changing.getName();
        _builder.append(_name_4, "");
        _builder.append(" TYPE ");
        {
          Type _type_4 = changing.getType();
          boolean _notEquals_2 = (!Objects.equal(_type_4, null));
          if (_notEquals_2) {
            Type _type_5 = changing.getType();
            String _name_5 = _type_5.getName();
            _builder.append(_name_5, "");
          } else {
            _builder.append("ANY");
          }
        }
        _builder.newLineIfNotEmpty();
      }
    }
    {
      EList<Parameter> _outputParameters_1 = operation.outputParameters();
      final Function1<Parameter, Boolean> _function_3 = (Parameter it) -> {
        ParameterDirectionKind _direction = it.getDirection();
        return Boolean.valueOf(_direction.equals(ParameterDirectionKind.RETURN_LITERAL));
      };
      Iterable<Parameter> _filter_3 = IterableExtensions.<Parameter>filter(_outputParameters_1, _function_3);
      boolean _hasElements_3 = false;
      for(final Parameter returning : _filter_3) {
        if (!_hasElements_3) {
          _hasElements_3 = true;
          _builder.append("RETURNING VALUE", "");
        }
        _builder.append("(");
        String _name_6 = returning.getName();
        _builder.append(_name_6, "");
        _builder.append(") TYPE ");
        {
          Type _type_6 = returning.getType();
          boolean _notEquals_3 = (!Objects.equal(_type_6, null));
          if (_notEquals_3) {
            Type _type_7 = returning.getType();
            String _name_7 = _type_7.getName();
            _builder.append(_name_7, "");
          } else {
            _builder.append("ANY");
          }
        }
        _builder.newLineIfNotEmpty();
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
    String _string = _generateParameters.toString();
    String _trim = _string.trim();
    _builder.append(_trim, "");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
}
