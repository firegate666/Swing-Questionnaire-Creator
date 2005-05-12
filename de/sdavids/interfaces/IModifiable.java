package de.sdavids.interfaces;

import java.util.Date;

public interface IModifiable {
	boolean isDirty();
	void setLastModified(Date date);
	Date getLastModified();
}