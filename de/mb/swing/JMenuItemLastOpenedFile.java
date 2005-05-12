package de.mb.swing;

import java.io.File;

import javax.swing.JMenuItem;

/**
 * @author Marco Behnke
 *
 */
public class JMenuItemLastOpenedFile extends JMenuItem {

    private String _filename;
    private int _index;

    /**
     * Public constructor
     * 
     * @param file Filepointer to store with
     * @param index Leading number
     */
    public JMenuItemLastOpenedFile(File file, int index) {
        this(file.getAbsolutePath(), index);
    }

    /**
     * Public constructor
     * 
     * @param text Filename to store with
     * @param index Leading number
     */
    public JMenuItemLastOpenedFile(String text, int index) {
        super(index + ". " + text);
        this.setIndex(index);
        this.setFilename(text);

    }

    /**
     * Set filename associated with menu item
     * 
     * @param pathname Filename
     */
    public void setFilename(String pathname) {
        this._filename = pathname;
    }

    /**
     * Get filename associated with menu item
     * 
     * @return absolute pathname
     */
    public String getFilename() {
        return _filename;
    }

    /**
     * Returns a file pointer to associated file
     * 
     * @return file pointer
     */
    public File getFile() {
        return new File(_filename);
    }

    /**
     * Returns the leading number of menu item.
     * @return leading number
     */
    public int getIndex() {
        return _index;
    }

    /**
     * Sets the leading number.
     * @param index leading number
     */
    public void setIndex(int index) {
        this._index = index;
    }

}
