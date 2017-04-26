package org.sklsft.generator.model.domain.business;

import java.util.ArrayList;
import java.util.List;

import org.sklsft.generator.model.domain.Package;
import org.sklsft.generator.model.domain.database.Table;
import org.sklsft.generator.model.domain.ui.BasicViewBean;
import org.sklsft.generator.model.domain.ui.FormBean;
import org.sklsft.generator.model.domain.ui.FullViewBean;
import org.sklsft.generator.model.domain.ui.ViewProperty;
import org.sklsft.generator.model.metadata.DetailMode;

/**
 * representation of a bean<br/>
 * Properties are willingly public because of their intensive use in file write
 * commands<br/>
 * The cardinality of a bean represents the number of fields that are needed to
 * build the business key (and its associated unique constraint)
 * 
 * @author Nicolas Thibault
 * 
 */
public class Bean {

	public Table table;
	public Package myPackage;
	public String className;
	public String objectName;

	public String baseDaoClassName;
	public String daoClassName;
	public String baseDaoInterfaceName;
	public String daoInterfaceName;
	public String daoObjectName;

	public String baseServiceClassName;
	public String serviceClassName;
	public String baseServiceInterfaceName;
	public String serviceInterfaceName;
	public String serviceObjectName;

	public String baseStateManagerClassName;
	public String stateManagerClassName;
	public String stateManagerObjectName;
	
	public String baseRightsManagerClassName;
	public String rightsManagerClassName;
	public String rightsManagerObjectName;
	
	public String baseProcessorClassName;
	public String processorClassName;
	public String processorObjectName;

	public String baseListControllerClassName;
	public String baseDetailControllerClassName;
	public String listControllerClassName;
	public String listControllerObjectName;
	public String detailControllerClassName;
	public String detailControllerObjectName;
	
	public String detailViewClassName;
	public String listViewClassName;
	public String detailViewObjectName;
	public String listViewObjectName;
	
	public int cardinality;
	public String listRendering;
	public String detailRendering;
	public boolean hasComboBox;
	public boolean createEnabled;
	public boolean updateEnabled;
	public boolean deleteEnabled;
	public DetailMode detailMode;
	
	public List<String> interfaces;
	public List<String> annotations;

	public List<Property> properties = new ArrayList<>();
	public List<OneToMany> oneToManyList = new ArrayList<>();
	public List<OneToManyComponent> oneToManyComponentList = new ArrayList<>();
	public List<OneToOne> oneToOneList = new ArrayList<>();
	public List<OneToOneComponent> oneToOneComponentList = new ArrayList<>();

	public boolean isComponent = false;
	public boolean isEmbedded = false;
	public boolean isOneToOneComponent = false;
	public Bean parentBean = null;
	
	public List<ViewProperty> referenceViewProperties = new ArrayList<>();
	public BasicViewBean basicViewBean;
	public FullViewBean fullViewBean;
	public FormBean formBean;
	
	
	/**
	 * determines whether the bean will have several tabs in the detail view (Mode Page)
	 * 
	 * @return
	 */
	public boolean hasTabsInDetailView() {
		return (this.oneToManyComponentList.size() > 0 || this.oneToManyList.size() > 0 || this.oneToOneComponentList.size() > 0 || this.oneToOneList.size() > 0);
	}
	

	

	
	
	/**
	 * get the list of aliases that will be used to build queries
	 * 
	 * @return
	 */
	public List<Alias> getReferenceAliases() {
		List<Alias> aliasList = new ArrayList<Alias>();
		List<Alias> tempAliasList = new ArrayList<Alias>();

		for (int i = 0; i < this.cardinality; i++) {
			Property currentProperty = this.properties.get(i);
			if (currentProperty.referenceBean != null) {
				Alias alias = new Alias();
				alias.propertyName = currentProperty.name;
				alias.name = currentProperty.capName;
				aliasList.add(alias);

				tempAliasList = currentProperty.referenceBean.getReferenceAliases();
				for (int j = 0; j < tempAliasList.size(); j++) {
					Alias currentAlias = tempAliasList.get(j);
					Alias tempAlias = new Alias();
					tempAlias.propertyName = alias.propertyName + "." + currentAlias.propertyName;
					tempAlias.name = alias.name + currentAlias.name;
					aliasList.add(tempAlias);
				}
			}
		}

		return aliasList;
	}
}
