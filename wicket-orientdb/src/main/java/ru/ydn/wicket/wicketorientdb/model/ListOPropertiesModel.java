package ru.ydn.wicket.wicketorientdb.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.lang.Args;

import ru.ydn.wicket.wicketorientdb.proto.OClassPrototyper;

import com.orientechnologies.orient.core.metadata.schema.OClass;
import com.orientechnologies.orient.core.metadata.schema.OProperty;

public class ListOPropertiesModel extends AbstractListModel<OProperty>
{
	private IModel<OClass> oClassModel;
	private IModel<Boolean> allPropertiesModel;
	public ListOPropertiesModel(final IModel<OClass> oClassModel, final IModel<Boolean> allPropertiesModel)
	{
		Args.notNull(oClassModel, "oClassModel");
		this.oClassModel = oClassModel;
		this.allPropertiesModel = allPropertiesModel;
	}

	@Override
	public Collection<OProperty> getData() {
		OClass oClass = oClassModel.getObject();
		if(oClass==null)
		{
			return null;
		}
		else if(allPropertiesModel==null||Boolean.TRUE.equals(allPropertiesModel.getObject()))
		{
			return oClass.properties();
		}
		else
		{
			return oClass.declaredProperties();
		}
	}

	@Override
	public void detach() {
		super.detach();
		if(allPropertiesModel!=null) allPropertiesModel.detach();
		oClassModel.detach();
	}

}
