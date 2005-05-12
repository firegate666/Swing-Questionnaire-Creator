package de.sdavids.beans;
import java.io.Serializable;

/**
 * .
 *
 * @author     @author    <a href="mailto:sdavids@gmx.de">Sebastian Davids</a>
 * @version SD 1.0.3 [01-09-03 04:11:03]
 */
public class StringArrayBean extends BoundBean implements java.io.Serializable {
    private String[] strings_;
    private java.lang.String[] fieldValue = null;

/**
 * StringArrayBean constructor comment.
 */
public StringArrayBean() {
    super();
}


/**
 * Gets the value property (java.lang.String[]) value.
 * @return The value property value.
 * @see #setValue
 */
public java.lang.String[] getValue() {
    return strings_;
}


/**
 * Gets the value index property (java.lang.String) value.
 * @return The value property value.
 * @param index The index value into the property array.
 * @see #setValue
 */
public java.lang.String getValue(int index) {
    return getValue()[index];
}


/**
 * Sets the value property (java.lang.String[]) value.
 * @param value The new value for the property.
 * @see #getValue
 */
public void setValue(String[] value) {
    String[] oldValue = strings_;
    strings_ = value;
    firePropertyChange("value", oldValue, value);
}


/**
 * Sets the value index property (java.lang.String[]) value.
 * @param index The index value into the property array.
 * @param value The new value for the property.
 * @see #getValue
 */
public void setValue(int index, String value) {
    String oldValue = strings_[index];
    strings_[index] = value;
    if (oldValue != null && !oldValue.equals(value)) {
        firePropertyChange("value", null, strings_);
    };
}
}