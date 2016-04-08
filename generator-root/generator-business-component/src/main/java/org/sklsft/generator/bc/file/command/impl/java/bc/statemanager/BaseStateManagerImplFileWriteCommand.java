package org.sklsft.generator.bc.file.command.impl.java.bc.statemanager;

import java.io.File;
import java.io.IOException;

import org.sklsft.generator.bc.file.command.impl.java.JavaFileWriteCommand;
import org.sklsft.generator.model.domain.business.Bean;
import org.sklsft.generator.model.domain.business.OneToManyComponent;
import org.sklsft.generator.model.domain.business.OneToOneComponent;

public class BaseStateManagerImplFileWriteCommand extends JavaFileWriteCommand {

	private Bean bean;

	/*
	 * constructor
	 */
	public BaseStateManagerImplFileWriteCommand(Bean bean) {
		super(bean.myPackage.model.project.workspaceFolder + File.separator + bean.myPackage.model.project.projectName + "-business-component" + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator
				+ bean.myPackage.baseStateManagerImplPackageName.replace(".", File.separator), bean.baseStateManagerClassName);

		this.bean = bean;
	}

	@Override
	protected void fetchSpecificImports() {

		javaImports.add("import org.sklsft.commons.api.exception.state.InvalidStateException;");
        javaImports.add("import " + this.bean.myPackage.omPackageName + "." + this.bean.className + ";");
        
        for (OneToOneComponent oneToOneComponent : this.bean.oneToOneComponentList)
        {
            Bean currentBean = oneToOneComponent.referenceBean;
            javaImports.add("import " + currentBean.myPackage.omPackageName + "." + currentBean.className + ";");
        }

        for (OneToManyComponent oneToManyComponent : this.bean.oneToManyComponentList)
        {
            Bean currentBean = oneToManyComponent.referenceBean;
            javaImports.add("import " + currentBean.myPackage.omPackageName + "." + currentBean.className + ";");
        }		
	}

	@Override
	protected void writeContent() throws IOException {

		writeLine("package " + this.bean.myPackage.baseStateManagerImplPackageName + ";");
        skipLine();
        
        writeImports();
        skipLine();

        writeLine("/**");
        writeLine(" * auto generated base state manager class file");
        writeLine(" * <br/>no modification should be done to this file");
		writeLine(" * <br/>processed by skeleton-generator");
        writeLine(" */");

        writeLine("public class " + this.bean.baseStateManagerClassName + " {");
        skipLine();
        
        writeLine("/**");
        writeLine(" * check before save");
        writeLine(" */");
        writeLine("public void checkBeforeSave(" + this.bean.className + " " + this.bean.objectName + ") throws InvalidStateException {");
        writeLine("// Empty by default. Can be overridden");        
        writeLine("}");
        skipLine();

        for (OneToOneComponent oneToOneComponent : this.bean.oneToOneComponentList)
        {
        	Bean currentBean = oneToOneComponent.referenceBean;
        	writeLine("/**");
            writeLine(" * check before save one to one compoennt " + currentBean.className);
            writeLine(" */");
            writeLine("public void checkBeforeSave" + currentBean.className + "(" + currentBean.className + " " + currentBean.objectName + "," + this.bean.className + " " + this.bean.objectName + ") throws InvalidStateException {");
            writeLine("// Empty by default. Can be overridden");
            writeLine("}");
            skipLine();
        }
        
        for (OneToManyComponent oneToManyComponent : this.bean.oneToManyComponentList)
        {
        	Bean currentBean = oneToManyComponent.referenceBean;
        	writeLine("/**");
            writeLine(" * check before save one to many compoennt " + currentBean.className);
            writeLine(" */");
            writeLine("public void checkBeforeSave" + currentBean.className + "(" + currentBean.className + " " + currentBean.objectName + "," + this.bean.className + " " + this.bean.objectName + ") throws InvalidStateException {");
            writeLine("// Empty by default. Can be overridden");
            writeLine("}");
            skipLine();
        }

        writeLine("/**");
        writeLine(" * check before update");
        writeLine(" */");
        writeLine("public void checkBeforeUpdate(" + this.bean.className + " " + this.bean.objectName + ") throws InvalidStateException {");
        writeLine("// Empty by default. Can be overridden");
        writeLine("}");
        skipLine();

        for (OneToOneComponent oneToOneComponent : this.bean.oneToOneComponentList)
        {
            Bean currentBean = oneToOneComponent.referenceBean;
            writeLine("/**");
            writeLine(" * check before update one to one component " + currentBean.className);
            writeLine(" */");
            writeLine("public void checkBeforeUpdate" + currentBean.className + "(" + currentBean.className + " " + currentBean.objectName + ") throws InvalidStateException {");
            writeLine("// Empty by default. Can be overridden");
            writeLine("}");
            skipLine();
        }

        for (OneToManyComponent oneToManyComponent : this.bean.oneToManyComponentList)
        {
            Bean currentBean = oneToManyComponent.referenceBean;
            writeLine("/**");
            writeLine(" * check before update one to many component " + currentBean.className);
            writeLine(" */");
            writeLine("public void checkBeforeUpdate" + currentBean.className + "(" + currentBean.className + " " + currentBean.objectName + ") throws InvalidStateException {");
            writeLine("// Empty by default. Can be overridden");
            writeLine("}");
            skipLine();
        }

        writeLine("/**");
        writeLine(" * check before delete");
        writeLine(" */");
        writeLine("public void checkBeforeDelete(" + this.bean.className + " " + this.bean.objectName + ") throws InvalidStateException {");
        writeLine("// Empty by default. Can be overridden");
        writeLine("}");
        skipLine();

        for (OneToOneComponent oneToOneComponent : this.bean.oneToOneComponentList)
        {
            Bean currentBean = oneToOneComponent.referenceBean;
            writeLine("/**");
            writeLine(" * check before delete one to one component " + currentBean.className);
            writeLine(" */");
            writeLine("public void checkBeforeDelete" + currentBean.className + "(" + currentBean.className + " " + currentBean.objectName + ") throws InvalidStateException {");
            writeLine("// Empty by default. Can be overridden");
            writeLine("}");
            skipLine();
        }
        
        for (OneToManyComponent oneToManyComponent : this.bean.oneToManyComponentList)
        {
            Bean currentBean = oneToManyComponent.referenceBean;
            writeLine("/**");
            writeLine(" * check before delete one to many component " + currentBean.className);
            writeLine(" */");
            writeLine("public void checkBeforeDelete" + currentBean.className + "(" + currentBean.className + " " + currentBean.objectName + ") throws InvalidStateException {");
            writeLine("// Empty by default. Can be overridden");
            writeLine("}");
            skipLine();
        }
        
        writeLine("}");
    }
}
