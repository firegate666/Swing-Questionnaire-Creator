package de.sdavids.dataobjects.common;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Date;

import de.sdavids.beans.BoundBean;
import de.sdavids.dataobjects.person.IPerson;
import de.sdavids.interfaces.IAuthored;

/**
 * @version 	1.0
 * @author
 */
public class VersionDescriptor extends BoundBean implements IAuthored {

	//////////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	//////////////////////////////////////////////////////////////////////////////

	/* ----------- Instance Attributes ---------- */
	
	/**
	 * The version's author.
	 * 
	 * R/W property.
	 */
	private IPerson fAuthor;

    /**
     * The version's creation date.
     * 
     * R property.
     */
    private Date fCreationDate;

	//////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	//////////////////////////////////////////////////////////////////////////////

    /**
     * Create a new version descriptor.
     *
     * The creation date will be now, i.e. <code>new Date()</code>.
     * 
	 * @param	<code>author/code> the author.
	 * @throws	<code>IllegalArgumentException</code> if either argument is
	 *    		<code>null</code>.
	 */	
	public VersionDescriptor(IPerson author) throws IllegalArgumentException {
		this(author, new Date());
	}
	
    /**
     * Create a new version descriptor.
     *
	 * @param	<code>author/code> the author.
     * @param	<code>date</code> the creation date.
	 * @throws	<code>IllegalArgumentException</code> if either argument is
	 *    		<code>null</code>.
	 */	
	public VersionDescriptor(IPerson author, Date created) throws IllegalArgumentException {
		setAuthor(author);
		setCreationDate(created);
	}
		
	//////////////////////////////////////////////////////////////////////////////
	// PUBLIC INTERFACE
	//////////////////////////////////////////////////////////////////////////////

    /* ----------- Mutators --------------------- */
    		
	/**
	 * Set the author.
	 * 
	 * @param	<code>author/code> the author.
	 * @throws	<code>IllegalArgumentException</code> if <code>author</code> is
	 *    		<code>null</code>.
	 */
	public void setAuthor(IPerson author) throws IllegalArgumentException {
		if (null == author)
			throw new IllegalArgumentException("author null");

		IPerson oldAuthor = getAuthor();

		if (author.equals(oldAuthor))
			return;

		fAuthor = author;

		fAuthor.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				firePropertyChange(evt);
			}
		});

		firePropertyChange("author", oldAuthor, author);
	}
			
    /* ----------- Accessors -------------------- */
        
	/**
	 * Answer the author.
	 *
	 * @return	the author.
	 */
	public IPerson getAuthor() {
		return fAuthor;
	}
	
    /**
     * Answer the creation date.
     *
     * @return	the creation date.
     */
    public Date getCreationDate() {
        return fCreationDate;
    }		

	//////////////////////////////////////////////////////////////////////////////
	// PROTECTED METHODS
	////////////////////////////////////////////////////////////////////////////// 
	    
    /* ----------- Mutators --------------------- */
    		
    /**
     * Set the creation date.
     *
     * @param	<code>date</code> the creation date.
     * @throws	<code>IllegalArgumentException</code> if <code>date</code> is
     *    		<code>null</code>.
     */
    protected void setCreationDate(Date date) throws IllegalArgumentException {
        if (null == date)
            throw new IllegalArgumentException("date null");
		
		fCreationDate = date;
    }
}
