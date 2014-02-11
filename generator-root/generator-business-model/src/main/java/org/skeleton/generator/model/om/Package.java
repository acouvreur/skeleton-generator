package org.skeleton.generator.model.om;

import java.util.List;


/**
 * representation of a package as a group of entities<br/>
 * the java entities will be organized through this classification as well as their corresponding components<br/>
 * Properties are willingly public because of their intensive use in file write commands<br/>
 * Nicolas Thibault
 *
 */
public class Package {

	public Model model;
    public String name;
    public List<Table> tables;
    public List<Bean> beans;

    public String omPackageName;
    public String ovPackageName;
    public String statusPackageName;

    public String baseDAOInterfacePackageName;
    public String baseDAOImplPackageName;
    public String DAOInterfacePackageName;
    public String DAOImplPackageName;

    public String baseServiceInterfacePackageName;
    public String baseServiceImplPackageName;
    public String serviceInterfacePackageName;
    public String serviceImplPackageName;

    public String baseMapperInterfacePackageName;
    public String baseMapperImplPackageName;
    public String mapperInterfacePackageName;
    public String mapperImplPackageName;

    public String baseStateManagerInterfacePackageName;
    public String baseStateManagerImplPackageName;
    public String stateManagerInterfacePackageName;
    public String stateManagerImplPackageName;

    public String facadeImplPackageName;
    public String facadeInterfacesPackageName;

    public String baseControllerPackageName;
    public String controllerPackageName;
    public String filterPackageName;

    public String builderPackageName;
    public String commandPackageName;
}
