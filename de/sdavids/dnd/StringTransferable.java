package de.sdavids.dnd;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
/**
 *  Description of the Class
 *
 *@author     Administrator
 *@created    29. Oktober 2001
 */
public class StringTransferable implements Transferable {

    /**
     *  Description of the Field
     */
    public final static DataFlavor localStringFlavor = DataFlavor.stringFlavor;

    /**
     *  Description of the Field
     */
    public final static DataFlavor[] typen =
            {
            StringTransferable.localStringFlavor
            };

    private final static List typListe = Arrays.asList(typen);
    private String string;


    /**
     *  Constructor for the StringTransferable object
     *
     *@param  string  Description of the Parameter
     */
    public StringTransferable(String string) {
        this.string = string;
    }


    /**
     *  Gets the transferDataFlavors attribute of the StringTransferable object
     *
     *@return    The transferDataFlavors value
     */
    public synchronized DataFlavor[] getTransferDataFlavors() {
        return typen;
    }


    /**
     *  Gets the dataFlavorSupported attribute of the StringTransferable object
     *
     *@param  flavor  Description of the Parameter
     *@return         The dataFlavorSupported value
     */
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return (typListe.contains(flavor));
    }


    /**
     *  Gets the transferData attribute of the StringTransferable object
     *
     *@param  flavor                          Description of the Parameter
     *@return                                 The transferData value
     *@exception  UnsupportedFlavorException  Description of the Exception
     *@exception  IOException                 Description of the Exception
     */
    public synchronized Object getTransferData(DataFlavor flavor)
             throws UnsupportedFlavorException, IOException {

        if (StringTransferable.localStringFlavor.equals(flavor)) {
            return this.string;
        } else {
            throw new UnsupportedFlavorException(flavor);
        }
    }

}
