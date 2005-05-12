package de.sdavids.dataobjects.person;

import de.sdavids.beans.IBoundBean;
import de.sdavids.interfaces.INullable;

//firstname != null != ""
//lastname != null != ""
//middlename != null 
public interface IPerson extends IBoundBean, INullable {
    void setFirstName(String firstName);
    void setMiddleName(String middleName);
    void setLastName(String lastName);

    String getFirstName();
	String getMiddleName();
	public String getLastName();
}