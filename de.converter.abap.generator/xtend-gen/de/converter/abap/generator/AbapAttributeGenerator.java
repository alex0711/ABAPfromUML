package de.converter.abap.generator;

import com.google.common.base.Objects;
import de.converter.abap.generator.AbapDocGenerator;
import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.VisibilityKind;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class AbapAttributeGenerator {
  public static CharSequence generateAttributes(final org.eclipse.uml2.uml.Class Class, final VisibilityKind Visibility) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<Property> _allAttributes = Class.getAllAttributes();
      for(final Property attribute : _allAttributes) {
        {
          VisibilityKind _visibility = attribute.getVisibility();
          boolean _equals = Objects.equal(_visibility, Visibility);
          if (_equals) {
            CharSequence _generateSingleAttribute = AbapAttributeGenerator.generateSingleAttribute(attribute);
            _builder.append(_generateSingleAttribute, "");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    return _builder;
  }
  
  public static CharSequence generateSingleAttribute(final Property property) {
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _generateAbapDoc = AbapDocGenerator.generateAbapDoc(property);
    _builder.append(_generateAbapDoc, "");
    _builder.newLineIfNotEmpty();
    {
      boolean _isStatic = property.isStatic();
      if (_isStatic) {
        _builder.append("CLASS-");
      }
    }
    _builder.append("DATA ");
    String _name = property.getName();
    _builder.append(_name, "");
    _builder.append(" TYPE ");
    {
      Type _type = property.getType();
      boolean _notEquals = (!Objects.equal(_type, null));
      if (_notEquals) {
        Type _type_1 = property.getType();
        String _name_1 = _type_1.getName();
        _builder.append(_name_1, "");
      } else {
        _builder.append("ANY");
      }
    }
    _builder.append(".");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
}
