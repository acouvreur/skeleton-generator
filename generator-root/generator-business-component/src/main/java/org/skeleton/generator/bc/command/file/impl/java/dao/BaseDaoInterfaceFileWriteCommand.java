package org.skeleton.generator.bc.command.file.impl.java.dao;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.skeleton.generator.bc.command.file.impl.java.JavaFileWriteCommand;
import org.skeleton.generator.model.om.Bean;
import org.skeleton.generator.model.om.Property;
import org.skeleton.generator.util.metadata.RelationType;

public class BaseDaoInterfaceFileWriteCommand extends JavaFileWriteCommand {

	private Bean bean;

	/*
	 * constructor
	 */
	public BaseDaoInterfaceFileWriteCommand(Bean bean) {

		super(bean.myPackage.model.project.workspaceFolder + File.separator + bean.myPackage.model.project.projectName + "-repository\\src\\main\\java\\"
				+ bean.myPackage.baseDAOInterfacePackageName.replace(".", "\\"), bean.baseDaoInterfaceName);

		this.bean = bean;

	}

	@Override
	protected void fetchSpecificImports() {

		javaImports.add("import java.util.List;");
		javaImports.add("import java.util.Date;");
		javaImports.add("import " + bean.myPackage.model.daoExceptionPackageName + ".ObjectNotFoundException;");
		javaImports.add("import " + bean.myPackage.omPackageName + "." + bean.className + ";");
	}

	@Override
	protected void writeContent() throws IOException {
		
		writeLine("package " + bean.myPackage.baseDAOInterfacePackageName + ";");
        
        
        writeImports();
        
        writeLine("/**");
        writeLine(" * auto generated base dao interface file");
        writeLine(" * <br/>no modification should be done to this file");
		writeLine(" * <br/>processed by skeleton-generator");
		writeLine(" */");
        writeLine("public interface " + this.bean.baseDaoInterfaceName + " {");
        skipLine();

        createLoadObjectList();
        createLoadObject();
        createExistsObject();
        createFindObject();
        createSaveObject();
        createUpdateObject();
        createDeleteObject();

        writeLine("}");
	}
	
	private void createLoadObjectList()
    {
        writeLine("/**"); 
        writeLine(" * load object list");
        writeLine(" */");
        writeLine("List<" + this.bean.className + "> load" + this.bean.className + "List();");
        skipLine();

        writeLine("/**"); 
        writeLine(" * load object list eagerly");
        writeLine(" */");
        writeLine("List<" + this.bean.className + "> load" + this.bean.className + "ListEagerly();");
        skipLine();

        for (Property property : this.bean.propertyList)
        {
            if (property.referenceBean != null && !property.relation.equals(RelationType.PROPERTY))
            {

                writeLine("/**");
                writeLine(" * load object list from list of " + property.referenceBean.objectName); 
                writeLine(" */");
                writeLine("List<" + this.bean.className + "> load" + this.bean.className + "ListFrom" + property.capName + "List (List<Long> " + property.name + "IdList);");
                skipLine();

                writeLine("/**");
                writeLine(" * load object list eagerly from list of " + property.referenceBean.objectName);
                writeLine(" */");
                writeLine("List<" + this.bean.className + "> load" + this.bean.className + "ListEagerlyFrom" + property.capName + "List (List<Long> " + property.name + "IdList);");
                skipLine();

            }
        }
    }

    private void createLoadObject()
    {
        writeLine("/**");
        writeLine(" * load object");
        writeLine(" */");
        writeLine(this.bean.className + " load" + this.bean.className + "(Long id);");
        skipLine();
    }

    private void createExistsObject()
    {
        List<Property> findPropertyList = this.bean.getFindPropertyList();

        writeLine("/**");
        writeLine(" * exists object");
        writeLine(" */");
        write("boolean exists" + this.bean.className + "(" + findPropertyList.get(0).beanDataType + " " + findPropertyList.get(0).name);
        for (int i = 1; i < findPropertyList.size(); i++)
        {
            write("," + findPropertyList.get(i).beanDataType + " " + findPropertyList.get(i).name);
        }
        writeLine(");");
        skipLine();
    }
    
    private void createFindObject()
    {
        List<Property> findPropertyList = this.bean.getFindPropertyList();

        writeLine("/**");
        writeLine(" * find object");  
        writeLine(" */");
        write(this.bean.className + " find" + this.bean.className + "(" + findPropertyList.get(0).beanDataType + " " + findPropertyList.get(0).name);
        for (int i=1;i<findPropertyList.size();i++)
        {
            write("," + findPropertyList.get(i).beanDataType + " " + findPropertyList.get(i).name);
        }
        writeLine(") throws ObjectNotFoundException;");
        skipLine();
    }

    private void createSaveObject()
    {
        writeLine("/**");
        writeLine("* save object");
        writeLine("*/");
        writeLine("Long save" + this.bean.className + "(" + this.bean.className + " " + this.bean.objectName + ");");
        skipLine();
    }

    private void createUpdateObject()
    {
        writeLine("/**"); 
        writeLine(" * update object");
        writeLine(" */");
        writeLine("void update" + this.bean.className + "(" + this.bean.className + " " + this.bean.objectName + ");");
        skipLine();
    }

    private void createDeleteObject()
    {
        writeLine("/**"); 
        writeLine(" * delete object");
        writeLine(" */");
        writeLine("void delete" + this.bean.className + "(" + this.bean.className + " " + this.bean.objectName + ");");
        skipLine();
    }
}
