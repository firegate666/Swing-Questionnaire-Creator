package de.sdavids.dataobjects.person;

import de.sdavids.beans.BoundBean;
import de.sdavids.interfaces.INullable;

class Person extends BoundBean implements IPerson {

	private String fFirstName;
	private String fMiddleName;
	private String fLastName;

	public Person() {
		super();

		//cannot use working constructor because "" is not valid
		fFirstName = "";
		fMiddleName = "";

		setMiddleName("");
	}

	public Person(String firstName, String lastName) {
		this(firstName, lastName, "");
	}

	public Person(String firstName, String lastName, String middleName) {
		setFirstName(firstName);
		setMiddleName(middleName);
		setLastName(lastName);
	}

	public void setFirstName(String firstName) {
		if (null == firstName)
			throw new IllegalArgumentException("firstName null");
		if ("".equals(firstName))
			throw new IllegalArgumentException("firstName empty string");

		String oldFirstName = fFirstName;

		fFirstName = firstName;

		firePropertyChange("firstName", oldFirstName, firstName);
	}

	public void setMiddleName(String middleName) {
		if (null == middleName)
			throw new IllegalArgumentException("middleName null");

		String oldMiddleName = fMiddleName;

		fMiddleName = middleName;

		firePropertyChange("middleName", oldMiddleName, middleName);
	}

	public void setLastName(String lastName) {
		if (null == lastName)
			throw new IllegalArgumentException("lastName null");
		if ("".equals(lastName))
			throw new IllegalArgumentException("lastName empty string");

		String oldLastName = fLastName;

		fLastName = lastName;

		firePropertyChange("lastName", oldLastName, lastName);
	}

	public String getFirstName() {
		return fFirstName;
	}

	public String getMiddleName() {
		return fMiddleName;
	}

	public String getLastName() {
		return fLastName;
	}

	public boolean isNull() {
		return false;
	}

	/**
	 * Compares two objects for equality.
	 *
	 * Returns a boolean that indicates whether this object's number and text
	 * are equivalent to the specified object's number and text.
	 *
	 * @param     <code>obj</code> the <code>Object</code> to compare with.
	 * @return    <code>true</code> if both object's questions are equal.
	 */
	public synchronized boolean equals(Object obj) {
		if (null == obj)
			return false;
		if (this == obj)
			return true;
		if (!(obj instanceof IPerson))
			return false;

		IPerson other = (IPerson) obj;

		if (!getFirstName().equals(other.getFirstName()))
			return false;
		if (!getLastName().equals(other.getLastName()))
			return false;
		if (!getMiddleName().equals(other.getMiddleName()))
			return false;

		return true;
	}

	public String toString() {
		StringBuffer result = new StringBuffer();

		result.append(getLastName());

		if (!"".equals(getFirstName())) {
			if (!"".equals(getLastName()))
				result.append(", ");

			result.append(getFirstName());
		}

		if (!"".equals(getMiddleName())) {
			result.append("".equals(getFirstName()) ? ", " : " ");
			result.append(" ").append(getMiddleName());
		}

		return result.toString();
	}
}