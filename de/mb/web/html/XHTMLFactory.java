package de.mb.web.html;


/**
 *  <code>XHTMLFactory</code> returns validated variable XHTML statements.
 *
 * @author     mb
 * @created    December 13, 2001
 */
public class XHTMLFactory extends HTMLFactory implements IHTMLFactory {

    /**
     *  if true, lazy xhtml-tags are used if possibly. if false every openeing
     *  tag is closed by normal closing tag
     */
    protected static boolean lazyXHTML = false;

    /**
     *  Builds anchor tag. Can be lazy tag.
     *
     * @param  anchorName  reference/anchor name, if empty no new tag is
     *      returned
     * @return             complete a-tag
     */
    public static String createAnchor(String anchorName) {
        if (anchorName.equalsIgnoreCase("")) {
            return "";
        }

        StringBuffer result = new StringBuffer(HTMLFactory.createAnchor(anchorName));

        if (lazyXHTML) {
            result.insert(result.length(), " \\");
        }

        else {
            result.append(buildTag(A, true));
        }

        return result.toString() + "\n";
    }

    /**
     *  Creates a single checkbox.tag
     *
     * @param  name       name of tag; to be referenced in form-tag
     * @param  value      item-value
     * @param  checked    is this box checked?
     * @param  innerText  item description, that is pu behind the checkbox
     * @return            complete input-type=checkbox-tag
     */
    public static String createCheckBox(
        String innerText,
        String name,
        String value,
        boolean checked) {
        StringBuffer result =
            new StringBuffer(HTMLFactory.createCheckBox(innerText, name, value, checked));

        result.append(buildTag(INPUT, true));

        return result.toString() + "\n";
    }

    /**
     *  Return hr-tag. Can be lazy tag.
     *
     * @param  noshade  set false if you want a shade
     * @return          complete hr-tag
     */
    public static String createHorizontalRule(boolean noshade) {
        StringBuffer result =
            new StringBuffer(HTMLFactory.createHorizontalRule(noshade));

        if (lazyXHTML) {
            result.insert(result.length(), " \\");
        }

        else {
            result.append(buildTag(HR, true));
        }

        return result.toString() + "\n";
    }

    /**
     *  return br-tag Can be lazy tag.
     *
     * @return    complete br-tag
     */
    public static String createLineBreak() {
        StringBuffer result = new StringBuffer(HTMLFactory.createLineBreak());

        if (lazyXHTML) {
            result.insert(result.length(), " \\");
        }

        else {
            result.append(buildTag(BR, true));
        }

        return result.toString() + "\n";
    }

    /**
     *  returns li-tag
     *
     * @param  innerText  item-description that stands behind li-tag
     * @return            complete li-tag
     */
    public static String createListItem(String innerText) {
        StringBuffer result = new StringBuffer(HTMLFactory.createListItem(innerText));

        result.append(buildTag(LI, true));

        return result.toString() + "\n";
    }

    /**
     *  returns option-tag which can be used in select tag.
     *
     * @param  innerText  item description that stands behind option-tag
     * @param  value      value that is returned if form is posted
     * @param  selected   if true, that box is checked
     * @return            complete option-tag
     */
    public static String createOption(
        String innerText,
        String value,
        boolean selected) {
        StringBuffer result =
            new StringBuffer(HTMLFactory.createOption(innerText, value, selected));

        result.append(buildTag(OPTION, true));

        return result.toString() + "\n";
    }

    /**
     *  Creates input-tag (type radiobutton).
     *
     * @param  innerText  item description that stands behind input tag
     * @param  name       name of tag; to be referenced in form-tag
     * @param  value      value that is returned if form is posted
     * @param  checked    if true, that box is checked
     * @return            complete input-tag (type radiobutton)
     */
    public static String createRadioButton(
        String innerText,
        String name,
        String value,
        boolean checked) {
        StringBuffer result =
            new StringBuffer(
                HTMLFactory.createRadioButton(innerText, name, value, checked));

        result.append(buildTag(INPUT, true));

        return result.toString() + "\n";
    }

    /**
     *  Creates input-tag (type resetbutton). Can be lazy tag.
     *
     * @param  value  Description that stands on the button
     * @return        complete input-tag (type resetbutton)
     */
    public static String createResetButton(String value) {
        StringBuffer result = new StringBuffer(HTMLFactory.createResetButton(value));

        if (lazyXHTML) {
            result.insert(result.length(), " \\");
        }

        else {
            result.append(buildTag(INPUT, true));
        }

        return result.toString() + "\n";
    }

    /**
     *  Creates input-tag (type submitbutton). Can be lazy tag.
     *
     * @param  value  Description that stands on the button
     * @return        complete input-tag (type submitbutton)
     */
    public static String createSubmitButtonCommon(String value) {
        StringBuffer result =
            new StringBuffer(HTMLFactory.createSubmitButtonCommon(value));

        if (lazyXHTML) {
            result.insert(result.length(), " \\");
        }

        else {
            result.append(buildTag(INPUT, true));
        }

        return result.toString() + "\n";
    }

    /**
     *  Creates input-tag (type graphical submitbutton). Can be lazy tag.
     *
     * @param  imageSrc  src location that point to image
     * @return           complete input-tag (type graphical submitbutton)
     */
    public static String createSubmitButtonGraphical(String imageSrc) {
        StringBuffer result =
            new StringBuffer(HTMLFactory.createSubmitButtonGraphical(imageSrc));

        if (lazyXHTML) {
            result.insert(result.length(), " \\");
        }

        else {
            result.append(buildTag(INPUT, true));
        }

        return result.toString() + "\n";
    }

    /**
     *  Creates input-tag (type textfield). Can be lazy tag.
     *
     * @param  value      default text
     * @param  name       name of tag; to be referenced in form-tag
     * @param  fieldSize  size of text field
     * @param  maxChar    maximum count of chars that can be entered
     * @param  readonly   sets textfield to readonly
     * @return            complete input-tag (type textfield)
     */
    public static String createTextField(
        String value,
        String name,
        int fieldSize,
        int maxChar,
        boolean readonly) {
        StringBuffer result =
            new StringBuffer(
                HTMLFactory.createTextField(value, name, fieldSize, maxChar, readonly));

        if (lazyXHTML) {
            result.insert(result.length(), " \\");
        }

        else {
            result.append(buildTag(INPUT, true));
        }

        return result.toString() + "\n";
    }

    /**
     *  Sets the lazyXHTML attribute of the XHTMLFactory class
     *
     * @param  lazy  The new lazyXHTML value
     */
    public static void setLazyXHTML(boolean lazy) {
        lazyXHTML = lazy;
    }
}