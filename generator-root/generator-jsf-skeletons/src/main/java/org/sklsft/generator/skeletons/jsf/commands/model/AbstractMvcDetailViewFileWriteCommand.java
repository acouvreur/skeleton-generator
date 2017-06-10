package org.sklsft.generator.skeletons.jsf.commands.model;

import java.io.File;
import java.io.IOException;

import org.sklsft.generator.model.domain.business.Bean;
import org.sklsft.generator.model.domain.business.OneToMany;
import org.sklsft.generator.model.domain.business.OneToManyComponent;
import org.sklsft.generator.model.domain.business.OneToOneComponent;
import org.sklsft.generator.skeletons.commands.impl.typed.JavaFileWriteCommand;

public abstract class AbstractMvcDetailViewFileWriteCommand extends JavaFileWriteCommand {

	private Bean bean;

	public AbstractMvcDetailViewFileWriteCommand(Bean bean) {
		super(bean.myPackage.model.project.workspaceFolder + File.separator + bean.myPackage.model.project.projectName + "-webapp" + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator
				+ bean.myPackage.detailViewPackageName.replace(".", File.separator), bean.detailViewClassName);

		this.bean = bean;
	}
	
	protected abstract void writeViewScope();
	
	protected abstract void writeViewScopeImport();

	@Override
	protected void fetchSpecificImports() {
		javaImports.add("import java.io.Serializable;");
		javaImports.add("import java.util.List;");
		javaImports.add("import org.springframework.stereotype.Component;");
        javaImports.add("import org.springframework.context.annotation.Scope;");
        javaImports.add("import org.springframework.web.context.WebApplicationContext;");
        
        writeViewScopeImport();
		
		javaImports.add("import " + this.bean.myPackage.fullViewsPackageName + "." + this.bean.fullViewBean.className + ";");
		
		for (OneToOneComponent oneToOneComponent : this.bean.oneToOneComponentList) {
			Bean currentBean = oneToOneComponent.referenceBean;
			javaImports.add("import " + currentBean.myPackage.fullViewsPackageName + "." + currentBean.fullViewBean.className + ";");
		}

		for (OneToManyComponent oneToManyComponent : this.bean.oneToManyComponentList) {
			Bean currentBean = oneToManyComponent.referenceBean;
			javaImports.add("import " + currentBean.myPackage.basicViewsPackageName + "." + currentBean.basicViewBean.className + ";");
			javaImports.add("import " + currentBean.myPackage.fullViewsPackageName + "." + currentBean.fullViewBean.className + ";");
			javaImports.add("import " + currentBean.myPackage.filtersPackageName + "." + currentBean.basicViewBean.filterClassName + ";");
		}
		
		for (OneToMany oneToMany : this.bean.oneToManyList) {
			Bean currentBean = oneToMany.referenceBean;
			javaImports.add("import " + currentBean.myPackage.basicViewsPackageName + "." + currentBean.basicViewBean.className + ";");
			javaImports.add("import " + currentBean.myPackage.fullViewsPackageName + "." + currentBean.fullViewBean.className + ";");
			javaImports.add("import " + currentBean.myPackage.filtersPackageName + "." + currentBean.basicViewBean.filterClassName + ";");
		}
	}

	@Override
	protected void writeContent() throws IOException {

		writeLine("package " + this.bean.myPackage.detailViewPackageName + ";");
		skipLine();

		writeImports();
		skipLine();

		writeLine("/**");
		writeLine(" * auto generated detail view class file");
		writeLine(" * <br/>write modifications between specific code marks");
        writeLine(" * <br/>processed by skeleton-generator");
		writeLine(" */");
		writeLine("@Component");
		writeViewScope();
		writeLine("public class " + this.bean.detailViewClassName + " implements Serializable {");
        skipLine();

        writeLine("private static final long serialVersionUID = 1L;");
        skipLine();

		writeLine("/*");
		writeLine(" * properties");
		writeLine(" */");
		writeLine("private " + this.bean.fullViewBean.className + " selected" + this.bean.className + " = new " + bean.fullViewBean.className + "();");
		skipLine();
		
		for (OneToOneComponent oneToOneComponent : this.bean.oneToOneComponentList) {
			Bean currentBean = oneToOneComponent.referenceBean;
			writeLine("private " + currentBean.fullViewBean.className + " selected" + oneToOneComponent.referenceBean.className + ";");
			skipLine();
		}
		
		

		for (OneToManyComponent oneToManyComponent : this.bean.oneToManyComponentList) {
			Bean currentBean = oneToManyComponent.referenceBean;
			writeLine("private List<" + currentBean.basicViewBean.className + "> " + currentBean.objectName + "List;");
			writeLine("private " + currentBean.basicViewBean.filterClassName + " " + currentBean.basicViewBean.filterObjectName + " = new " + currentBean.basicViewBean.filterClassName + "();");
			writeLine("private " + currentBean.fullViewBean.className + " selected" + currentBean.className + ";");
			
			skipLine();
		}
		
		
		for (OneToMany oneToMany : this.bean.oneToManyList) {
			Bean currentBean = oneToMany.referenceBean;
			writeLine("private List<" + currentBean.basicViewBean.className + "> " + currentBean.objectName + "List;");
			writeLine("private " + currentBean.basicViewBean.filterClassName + " " + currentBean.basicViewBean.filterObjectName + " = new " + currentBean.basicViewBean.filterClassName + "();");
			writeLine("private " + currentBean.fullViewBean.className + " selected" + currentBean.className + ";");
			skipLine();
		}
		

		writeLine("/*");
		writeLine(" * getters and setters");
		writeLine(" */");		
		writeLine("public " + this.bean.fullViewBean.className + " getSelected" + this.bean.className + "() {");
		writeLine("return selected" + this.bean.className + ";");
		writeLine("}");
		writeLine("public void setSelected" + this.bean.className + "(" + this.bean.fullViewBean.className + " selected" + this.bean.className + ") {");
		writeLine("this.selected" + this.bean.className + " = selected" + this.bean.className + ";");
		writeLine("}");
		skipLine();

		for (OneToOneComponent oneToOneComponent : this.bean.oneToOneComponentList) {
			Bean currentBean = oneToOneComponent.referenceBean;
			writeLine("public " + currentBean.fullViewBean.className + " getSelected" + currentBean.className + "() {");
			writeLine("return selected" + currentBean.className + ";");
			writeLine("}");

			writeLine("public void setSelected" + currentBean.className + "(" + currentBean.fullViewBean.className + " selected" + currentBean.className + ") {");
			writeLine("this.selected" + currentBean.className + " = selected" + currentBean.className + ";");
			writeLine("}");
			skipLine();
		}

		for (OneToManyComponent oneToManyComponent : this.bean.oneToManyComponentList) {
			Bean currentBean = oneToManyComponent.referenceBean;
			writeLine("public List<" + currentBean.basicViewBean.className + "> get" + currentBean.className + "List() {");
			writeLine("return " + currentBean.objectName + "List;");
			writeLine("}");

			writeLine("public void set" + currentBean.className + "List(List<" + currentBean.basicViewBean.className + "> " + currentBean.objectName + "List) {");
			writeLine("this." + currentBean.objectName + "List = " + currentBean.objectName + "List;");
			writeLine("}");
			skipLine();
			
			writeLine("public " + currentBean.basicViewBean.filterClassName + " get" + currentBean.basicViewBean.filterClassName + "() {");
			writeLine("return " + currentBean.basicViewBean.filterObjectName + ";");
			writeLine("}");

			writeLine("public void set" + currentBean.basicViewBean.filterClassName + "(" + currentBean.basicViewBean.filterClassName + " " + currentBean.basicViewBean.filterObjectName + ") {");
			writeLine("this." + currentBean.basicViewBean.filterObjectName + " = " + currentBean.basicViewBean.filterObjectName + ";");
			writeLine("}");
			skipLine();

			writeLine("public " + currentBean.fullViewBean.className + " getSelected" + currentBean.className + "() {");
			writeLine("return selected" + currentBean.className + ";");
			writeLine("}");

			writeLine("public void setSelected" + currentBean.className + "(" + currentBean.fullViewBean.className + " selected" + currentBean.className + ") {");
			writeLine("this.selected" + currentBean.className + " = selected" + currentBean.className + ";");
			writeLine("}");
			skipLine();
			
		}
		
		for (OneToMany oneToMany : this.bean.oneToManyList) {
			Bean currentBean = oneToMany.referenceBean;
			writeLine("public List<" + currentBean.basicViewBean.className + "> get" + currentBean.className + "List() {");
			writeLine("return " + currentBean.objectName + "List;");
			writeLine("}");

			writeLine("public void set" + currentBean.className + "List(List<" + currentBean.basicViewBean.className + "> " + currentBean.objectName + "List) {");
			writeLine("this." + currentBean.objectName + "List = " + currentBean.objectName + "List;");
			writeLine("}");
			skipLine();
			
			writeLine("public " + currentBean.basicViewBean.filterClassName + " get" + currentBean.basicViewBean.filterClassName + "() {");
			writeLine("return " + currentBean.basicViewBean.filterObjectName + ";");
			writeLine("}");

			writeLine("public void set" + currentBean.basicViewBean.filterClassName + "(" + currentBean.basicViewBean.filterClassName + " " + currentBean.basicViewBean.filterObjectName + ") {");
			writeLine("this." + currentBean.basicViewBean.filterObjectName + " = " + currentBean.basicViewBean.filterObjectName + ";");
			writeLine("}");
			skipLine();

			writeLine("public " + currentBean.fullViewBean.className + " getSelected" + currentBean.className + "() {");
			writeLine("return selected" + currentBean.className + ";");
			writeLine("}");

			writeLine("public void setSelected" + currentBean.className + "(" + currentBean.fullViewBean.className + " selected" + currentBean.className + ") {");
			writeLine("this.selected" + currentBean.className + " = selected" + currentBean.className + ";");
			writeLine("}");
			skipLine();
			
		}

		writeNotOverridableContent();

		writeLine("}");

	}
}