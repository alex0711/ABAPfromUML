package de.converter.abap.ui.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.resource.UMLResource;
import org.eclipse.xtext.builder.EclipseResourceFileSystemAccess2;
import org.eclipse.xtext.generator.JavaIoFileSystemAccess;
import org.eclipse.xtext.parser.IEncodingProvider;
import org.eclipse.xtext.service.AbstractGenericModule;
import com.google.inject.Guice;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.emf.common.util.URI;
import de.converter.abap.generator.*;

public class ConvertHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);

		ISelection selection = HandlerUtil.getCurrentSelection(event);
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			Object firstElement = structuredSelection.getFirstElement();
			if (firstElement instanceof IFile) {
				IFile file = (IFile) firstElement;
				IProject project = file.getProject();
				IFolder srcGenFolder = project.getFolder("src-gen");
				if (!srcGenFolder.exists()) {
					try {
						System.out.println("Src-Gen folder does not yet exist");
						srcGenFolder.create(true, true, new NullProgressMonitor());
					} catch (CoreException e) {
						return null;
					}
				}
				
				MessageDialog.openInformation(window.getShell(), "You have clicked on a valid source file",
						"ABAP Code Generation starts with" + "\n\n"
						+ "Project path: " + project.getName().toString() + "\n"
						+ "SrcGen path: " + srcGenFolder.getName().toString() + "\n"
						+ "File path: " + file.getFullPath().toString());
				System.out.println("Project path: " + project.getName().toString());
				System.out.println("SrcGen path: " + srcGenFolder.getName().toString());
				System.out.println("File path: " + file.getFullPath().toString());			
				
//				final EclipseResourceFileSystemAccess2 fsa = new EclipseResourceFileSystemAccess2();
//				fsa.setOutputPath(srcGenFolder.getFullPath().toString());
//				fsa.setProject(project);
//				fsa.setMonitor(new NullProgressMonitor());
//				fsa.setPostProcessor(new EclipseResourceFileSystemAccess2.IFileCallback() {
//					@Override
//					public boolean beforeFileDeletion(IFile arg0) {
//						return true;
//					}
//					@Override
//					public void afterFileUpdate(IFile arg0) {						
//					}
//					@Override
//					public void afterFileCreation(IFile arg0) {		
//						System.out.println("File created " + arg0.getFullPath().toString());
//					}
//				});
				
				JavaIoFileSystemAccess fsa = new JavaIoFileSystemAccess();
				fsa.setOutputPath(srcGenFolder.getLocation().toOSString());
				Guice.createInjector(new AbstractGenericModule() {
					public Class<? extends IEncodingProvider> bindIEncodingProvider() {
						return IEncodingProvider.Runtime.class;
					}
				}).injectMembers(fsa);
				
			    URI uri = URI.createPlatformResourceURI(file.getFullPath().toString(), true);
				ResourceSet rs = new ResourceSetImpl();
				rs.getPackageRegistry().put(UMLPackage.eNS_URI, UMLPackage.eINSTANCE);
				rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put(UMLResource.FILE_EXTENSION, UMLResource.Factory.INSTANCE);
				Resource r = rs.getResource(uri, true);

				MainGenerator Generator = new MainGenerator();
				Generator.doGenerate(r, fsa);
			}
		}
		return null;
	}
}