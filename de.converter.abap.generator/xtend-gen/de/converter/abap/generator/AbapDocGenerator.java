package de.converter.abap.generator;

import com.google.common.base.Objects;
import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Comment;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.VisibilityKind;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class AbapDocGenerator {
  public static CharSequence generateAbapDoc(final Property property) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\"! ");
    VisibilityKind _visibility = property.getVisibility();
    _builder.append(_visibility, "");
    _builder.append(" ");
    {
      boolean _isStatic = property.isStatic();
      if (_isStatic) {
        _builder.append("Static ");
      } else {
        _builder.append("Instance ");
      }
    }
    _builder.append("Attribute ");
    String _name = property.getName();
    _builder.append(_name, "");
    _builder.newLineIfNotEmpty();
    {
      EList<Comment> _ownedComments = property.getOwnedComments();
      for(final Comment comment : _ownedComments) {
        _builder.append("\"! ");
        String _body = comment.getBody();
        String _string = _body.toString();
        _builder.append(_string, "");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  public static CharSequence generateAbapDoc(final Operation operation) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\"! ");
    VisibilityKind _visibility = operation.getVisibility();
    _builder.append(_visibility, "");
    _builder.append(" ");
    {
      boolean _isStatic = operation.isStatic();
      if (_isStatic) {
        _builder.append("Static ");
      } else {
        _builder.append("Instance ");
      }
    }
    _builder.append("Method ");
    String _name = operation.getName();
    _builder.append(_name, "");
    _builder.newLineIfNotEmpty();
    {
      EList<Comment> _ownedComments = operation.getOwnedComments();
      for(final Comment comment : _ownedComments) {
        _builder.append("\"! ");
        String _body = comment.getBody();
        String _string = _body.toString();
        _builder.append(_string, "");
        _builder.newLineIfNotEmpty();
      }
    }
    {
      EList<Parameter> _inputParameters = operation.inputParameters();
      for(final Parameter input : _inputParameters) {
        _builder.append("\"! @parameter ");
        String _name_1 = input.getName();
        _builder.append(_name_1, "");
        _builder.append(" | TYPE ");
        {
          Type _type = input.getType();
          boolean _notEquals = (!Objects.equal(_type, null));
          if (_notEquals) {
            Type _type_1 = input.getType();
            String _name_2 = _type_1.getName();
            _builder.append(_name_2, "");
          }
        }
        _builder.newLineIfNotEmpty();
      }
    }
    {
      EList<Parameter> _outputParameters = operation.outputParameters();
      for(final Parameter output : _outputParameters) {
        _builder.append("\"! @parameter ");
        String _name_3 = output.getName();
        _builder.append(_name_3, "");
        _builder.append(" | TYPE ");
        {
          Type _type_2 = output.getType();
          boolean _notEquals_1 = (!Objects.equal(_type_2, null));
          if (_notEquals_1) {
            Type _type_3 = output.getType();
            String _name_4 = _type_3.getName();
            _builder.append(_name_4, "");
          }
        }
        _builder.newLineIfNotEmpty();
      }
    }
    {
      EList<Type> _raisedExceptions = operation.getRaisedExceptions();
      for(final Type raising : _raisedExceptions) {
        _builder.append("\"! @raising ");
        String _name_5 = raising.getName();
        _builder.append(_name_5, "");
        _builder.append(" |");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
}
