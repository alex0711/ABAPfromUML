package de.converter.abap.generator;

import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import de.converter.abap.generator.CodeGenFacade;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.generator.IGenerator;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;

@SuppressWarnings("all")
public class MainGenerator implements IGenerator {
  private CodeGenFacade CodeGenerator = new CodeGenFacade();
  
  @Override
  public void doGenerate(final Resource resource, final IFileSystemAccess fsa) {
    TreeIterator<EObject> _allContents = resource.getAllContents();
    Iterable<EObject> _iterable = IteratorExtensions.<EObject>toIterable(_allContents);
    Iterable<Classifier> _filter = Iterables.<Classifier>filter(_iterable, Classifier.class);
    for (final Classifier classifier : _filter) {
      {
        String _name = classifier.getName();
        String _string = _name.toString();
        String _plus = ("Classifier found: " + _string);
        System.out.println(_plus);
        this.CodeGenerator.setClassifier(classifier);
        final CharSequence generatedCode = this.CodeGenerator.getCode();
        boolean _notEquals = (!Objects.equal(generatedCode, null));
        if (_notEquals) {
          String _name_1 = classifier.getName();
          String _plus_1 = (_name_1 + ".txt");
          fsa.generateFile(_plus_1, generatedCode);
          System.out.println(("Generated: " + generatedCode));
        }
      }
    }
  }
}
