package org.sklsft.generator.bc.file.command.impl.conf.java.junit;

import java.io.File;

import org.sklsft.generator.bc.file.command.impl.templatized.ProjectTemplatizedFileWriteCommand;
import org.sklsft.generator.model.metadata.FileType;
import org.sklsft.generator.model.om.Project;

public class BuildFailureExceptionFileWriteCommand extends ProjectTemplatizedFileWriteCommand {

	public BuildFailureExceptionFileWriteCommand(Project project) {
		super(project.workspaceFolder + File.separator + project.projectName + "-services/src/test/java/" + project.model.testExceptionPackageName.replace(".", File.separator) + File.separator, "BuildFailureException", FileType.JAVA, project);
	}

}