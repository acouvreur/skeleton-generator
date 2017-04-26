package org.sklsft.generator.skeletons.core.commands.api.model;

import java.io.File;
import java.io.IOException;

import org.sklsft.generator.model.domain.business.Bean;
import org.sklsft.generator.model.domain.ui.ViewProperty;
import org.sklsft.generator.skeletons.commands.impl.typed.JavaFileWriteCommand;

public class FilterFileWriteCommand extends JavaFileWriteCommand {

	private Bean bean;

	public FilterFileWriteCommand(Bean bean) {
		super(bean.myPackage.model.project.workspaceFolder + File.separator + bean.myPackage.model.project.projectName + "-api" + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator
				+ bean.myPackage.filtersPackageName.replace(".", File.separator), bean.basicViewBean.filterClassName);

		this.bean = bean;
	}

	@Override
	protected void fetchSpecificImports() {
		javaImports.add("import java.util.Date;");
		javaImports.add("import java.io.Serializable;");
	}

	@Override
	protected void writeContent() throws IOException {
		writeLine("package " + bean.myPackage.filtersPackageName + ";");
		skipLine();

		writeImports();
		skipLine();

		writeLine("/**");
		writeLine(" * auto generated filter class file");
		writeLine(" * <br/>basic representation of filter used along with jsf datatable");
		writeLine(" * <br/>write modifications between specific code marks");
		writeLine(" * <br/>processed by skeleton-generator");
		writeLine(" */");
		writeLine("public class " + this.bean.basicViewBean.filterClassName + " implements Serializable {");
		skipLine();

		writeLine("private static final long serialVersionUID = 1L;");
		skipLine();

		createProperties();
		createGettersAndSetters();
		writeNotOverridableContent();

		writeLine("}");

	}

	private void createProperties() {
		writeLine("/*");
		writeLine(" * properties");
		writeLine(" */");
		
		for (ViewProperty property:this.bean.basicViewBean.properties) {
			writeLine("private String " + property.name + ";");
		}
		skipLine();

	}

	private void createGettersAndSetters() {
		writeLine("/*");
		writeLine(" * getters and setters");
		writeLine(" */");

		for (ViewProperty property:this.bean.basicViewBean.properties) {
			writeLine("public String get" + property.capName + "() {");
			writeLine("return this." + property.name + ";");
			writeLine("}");
			skipLine();

			writeLine("public void set" + property.capName + "(String " + property.name + ") {");
			writeLine("this." + property.name + " = " + property.name + ";");
			writeLine("}");
			skipLine();
		}
		skipLine();
	}
}
