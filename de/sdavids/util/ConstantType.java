package de.sdavids.util;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.TreeMap;
import java.io.ObjectInputStream;
import java.io.IOException;public class ConstantType
            implements Serializable {

    private int value_;
    private transient String description_;

    /**
     * <code>Map</code> containing <code>SortedMap</code>s.
     */
    private static final Map types_ = new HashMap();

    protected ConstantType(int value, String description)
                throws IllegalArgumentException {

        if (null == description) throw new IllegalArgumentException("null");

        value_       = value;
        description_ = description;

        checkForDuplicates(this);
        storeType(this);
    }

    //precondition: type != null
    private void checkForDuplicates(ConstantType type) {
        String className = type.getClass().getName();

        Map values = (Map) types_.get(className);

        if (null == values) return;
        if (null == values.get(new Integer(type.getValue()))) return;

        throw new RuntimeException("No duplicates allowed: " + className + "=" + type);
    }

    public boolean equals(Object obj) {
        if (null == obj) return false;
        if (this == obj) return true;
        if (!(obj instanceof ConstantType)) return false;
        if (getClass() != obj.getClass()) return false;

        return (getValue() == ((ConstantType) obj).getValue());
    }

    public static ConstantType getByValue(Class classRef, int value)
                throws IllegalArgumentException, NoSuchElementException {

        if (null == classRef)
            throw new IllegalArgumentException("null");

        String className = classRef.getName();

        if (!types_.containsKey(className))
            throw new NoSuchElementException("no key " + className);

        Map values = (Map) types_.get(className);

        if (null == values)
            throw new NoSuchElementException(className + " has no values");

        ConstantType result = (ConstantType) values.get(new Integer(value));

        if (null == result)
            throw new NoSuchElementException("no value " + value);

        return result;
    }

    /**
     * Answer the description.
     *
     * @return    the description.
     */
    public String getDescription() {
        return description_;
    }

    public static int getMaxValue(Class classRef )
                throws IllegalArgumentException, NoSuchElementException {

        Iterator it = iterator(classRef);

        if (!it.hasNext())
            throw new IllegalArgumentException("no values");

        int result = ((ConstantType) it.next()).getValue();

        while (it.hasNext()) {
            Math.max(result, ((ConstantType) it.next()).getValue());
        }

        return result;
    }

    /**
     * Answer the value.
     *
     * @return    the value.
     */
    public int getValue() {
        return value_;
    }

    /**
     * Generates a hash code for the constant.
     *
     * <p>This method is supported primarily for hash tables, such as those provided
     * in <code>java.util.Hashtable</code>.</p>
     *
     * @return    an integer hash code for the status.
     * @see       java.util.Hashtable
     */
    public int hashCode() {
        return getValue();
    }

    public static Iterator iterator(Class classRef)
                throws IllegalArgumentException, NoSuchElementException {

        if (null == classRef)
            throw new IllegalArgumentException("null");

        String className = classRef.getName();

        if (!types_.containsKey(className))
            throw new NoSuchElementException("no key " + className);

        Map values = (Map) types_.get(className);

        if (null == values)
            throw new NoSuchElementException(className + " has no values");

        return values.entrySet().iterator();
    }

    private void readObject(ObjectInputStream stream)
                throws IOException, ClassNotFoundException {

        stream.defaultReadObject();

        ConstantType ct = getByValue(getClass(), getValue());

        description_ = ct.getDescription();
    }

    //precondition: type != null
    // Double-checked locking ideom: http://www.javaworld.com/javaworld/javatips/jw-javatip67.html
    private void storeType(ConstantType type) {
        String className = type.getClass().getName();

        if (!types_.containsKey(className)) {
            synchronized(types_) {
                if (!types_.containsKey(className)) types_.put(className, new TreeMap());
            }
        }

        Map values = (Map) types_.get(className);

        values.put(new Integer(type.getValue()), type);
    }

    /**
     * Returns a string representation of the object.
     *
     * @return    a string representation of the object.
     */
    public String toString() {
        return getDescription();
    }
}