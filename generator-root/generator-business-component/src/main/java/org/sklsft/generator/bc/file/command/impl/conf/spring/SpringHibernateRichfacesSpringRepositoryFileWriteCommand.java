package org.sklsft.generator.bc.file.command.impl.conf.spring;

import java.io.File;

import org.sklsft.generator.bc.file.command.impl.templatized.ProjectTemplatizedFileWriteCommand;
import org.sklsft.generator.model.metadata.FileType;
import org.sklsft.generator.model.om.Project;

public class SpringHibernateRichfacesSpringRepositoryFileWriteCommand extends ProjectTemplatizedFileWriteCommand {

	public SpringHibernateRichfacesSpringRepositoryFileWriteCommand(Project project) {
		super(project.workspaceFolder + File.separator + project.projectName + "-webapp/src/main/resources", "applicationContext-" + project.projectName + "-repository", FileType.XML, project);
	}

}
