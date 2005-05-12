package de.mb.web.html;


/**
 *  <code>HTMLFactory</code> returns validated variable HTML statements.
 *
 * @author     mb
 * @created    December 9, 2001
 */
public class HTMLFactory extends Object implements IHTMLFactory {

    /**  Description of the Field */
    protected final static String A = "a ";
    /**  Description of the Field */
    protected final static String B = "b ";
    /**  Description of the Field */
    protected final static String BODY = "body ";
    /**  Description of the Field */
    protected final static String BR = "br";
    /**  Description of the Field */
    protected final static String DD = "dd ";
    /**  Description of the Field */
    protected final static String DIV = "div ";
    /**  Description of the Field */
    protected final static String DL = "dl ";
    /**  Description of the Field */
    protected final static String DT = "dt ";
    /**  Description of the Field */
    protected final static String FORM = "form ";
    /**  Description of the Field */
    protected final static String H = "h";
    /**  Description of the Field */
    protected final static String HEAD = "head ";
    /**  Description of the Field */
    protected final static String HR = "hr ";
    /**  Description of the Field */
    protected final static String HTML = "html ";
    /**  Description of the Field */
    protected final static String I = "u ";
    /**  Description of the Field */
    protected final static String INPUT = "input ";
    /**  Description of the Field */
    protected final static String LI = "li";
    /**  Description of the Field */
    protected final static String NOBR = "nobr ";
    /**  Description of the Field */
    protected final static String OL = "ol";
    /**  Description of the Field */
    protected final static String OPTION = "option ";
    /**  Description of the Field */
    protected final static String P = "p ";
    /**  Description of the Field */
    protected final static String S = "s ";
    /**  Description of the Field */
    protected final static String SELECT = "select ";
    /**  Description of the Field */
    protected final static String SUB = "s ";
    /**  Description of the Field */
    protected final static String SUP = "s ";
    /**  Description of the Field */
    protected final static String TABLE = "table ";
    /**  Description of the Field */
    protected final static String TAG_CLOSING = "/";
    /**  Description of the Field */
    protected final static String TAG_END = ">";
    /**  Description of the Field */
    protected final static String TAG_START = "<";
    /**  Description of the Field */
    protected final static String TD = "td ";
    /**  Description of the Field */
    protected final static String TEXTAREA = "textarea ";
    /**  Description of the Field */
    protected final static String TH = "th ";
    /**  Description of the Field */
    protected final static String TITLE = "title ";
    /**  Description of the Field */
    protected final static String TR = "tr ";
    /**  Description of the Field */
    protected final static String U = "i ";
    /**  Description of the Field */
    protected final static String UL = "ul";

    /**
     *  Builds anchor tag.
     *
     * @param  anchorName  reference/anchor name, if empty no new tag is
     *      returned
     * @return             complete a-tag
     */
    public static String createAnchor(String anchorName) {
        if (anchorName.equalsIgnoreCase(""))
            return "";

        StringBuffer result = new StringBuffer();
        String tagDefinition = A;

        tagDefinition += "name=\"" + anchorName + "\" ";

        result.append(buildTag(tagDefinition));
        result.append(buildTag(BODY, true));

        return result.toString();
    }

    /**
     *  Creates the body surroundings of a HTML document.
     *
     * @param  innerText  is set between start- and closing-tag
     * @return            complete body-tag
     */
    public static String createBody(String innerText) {
        StringBuffer result = new StringBuffer();
        String tagDefinition = BODY;

        result.append(buildTag(tagDefinition) + "\n");
        result.append(innerText);
        result.append(buildTag(BODY, true) + "\n");

        return result.toString();
    }

    /**
     *  Sets innerText in bold-tags.
     *
     * @param  innerText  text between start- and closing-tag, if empty no tag
     *      is returned
     * @return            complete b-tag
     */
    public static String createBold(String innerText) {
        if (innerText.equalsIgnoreCase(""))
            return "";

        StringBuffer result = new StringBuffer();
        String tagDefinition = B;

        result.append(buildTag(tagDefinition));
        result.append(innerText);
        result.append(buildTag(B, true));

        return result.toString();
    }

    /**
     *  Creates a single checkbox.tag
     *
     * @param  name       name of tag; to be referenced in form-tag
     * @param  value      item-value
     * @param  checked    is this box checked?
     * @param  innerText  Description of the Parameter
     * @return            complete input-type=checkbox-tag
     */
    public static String createCheckBox(
        String innerText,
        String name,
        String value,
        boolean checked) {
        StringBuffer result = new StringBuffer();
        String tagDefinition = INPUT;

        tagDefinition += "type=\"checkbox\" ";
        tagDefinition += "name=\"" + name + "\" ";
        tagDefinition += "value=\"" + value + "\" ";
        if (checked)
            tagDefinition += "checked=\"checked\" ";

        result.append(buildTag(tagDefinition));
        result.append(innerText);
        result.append(buildTag(INPUT, true));

        return result.toString();
    }

    public static String createComment(String comment) {
        return "<!--" + comment + "-->";

    }

    /**
     *  Description of the Method
     *
     * @param  innerText  Description of the Parameter
     * @return            Description of the Return Value
     */
    public static String createDescriptionList(String innerText) {
        StringBuffer result = new StringBuffer();
        String tagDefinition = DL;

        result.append(buildTag(tagDefinition));
        result.append(innerText);
        result.append(buildTag(DL, true));

        return result.toString();
    }

    /**
     *  Description of the Method
     *
     * @param  descriptionTerm         Description of the Parameter
     * @param  descriptionDescription  Description of the Parameter
     * @return                         Description of the Return Value
     */
    public static String createDescriptionListItem(
        String descriptionTerm,
        String descriptionDescription) {
        StringBuffer result = new StringBuffer();
        String tagDefinition1 = DT;
        String tagDefinition2 = DD;

        result.append(buildTag(tagDefinition1));
        result.append(descriptionTerm);
        result.append(buildTag(DT, true));
        result.append(buildTag(tagDefinition2));
        result.append(descriptionDescription);
        result.append(buildTag(DD, true));

        return result.toString();
    }

    /**
     *  Gets the div attribute of the HTMLFactory object
     *
     * @param  id         Description of the Parameter
     * @param  innerText  Description of the Parameter
     * @param  alignment  Description of the Parameter
     * @return            The div value
     */
    public static String createDiv(String innerText, String id, String alignment) {
        StringBuffer result = new StringBuffer();
        String tagDefinition = DIV;

        tagDefinition += "id=\"" + id + "\" ";
        tagDefinition += "align=\"" + alignment + "\" ";

        result.append(buildTag(tagDefinition));
        result.append(innerText);
        result.append(buildTag(DIV, true));

        return result.toString();
    }

    /**
     *  Creates form-tag. Value for submit-handling are given as well.
     *
     * @param  name       name of form; to be referenced within document
     * @param  action     submit-adress
     * @param  method     should I <code>get</code> or <code>post</code> the
     *      results?
     * @param  innerText  deklaration of form contents. Can be normal text,
     *      buttons, lists ...
     * @return            complete form-tag
     */
    public static String createForm(
        String innerText,
        String name,
        String action,
        String method) {
        StringBuffer result = new StringBuffer();
        String tagDefinition = FORM;

        tagDefinition += "name=\"" + name + "\" ";
        tagDefinition += "action=\"" + action + "\" ";
        tagDefinition += "method=\"" + method + "\" ";

        result.append(buildTag(tagDefinition) + "\n");
        result.append(innerText);
        result.append(buildTag(FORM, true) + "\n");

        return result.toString();
    }

    /**
     *  Gets the head attribute of the HTMLFactory object
     *
     * @param  innerText  Description of the Parameter
     * @return            The head value
     */
    public static String createHead(String innerText) {
        StringBuffer result = new StringBuffer();
        String tagDefinition = HEAD;

        result.append(buildTag(tagDefinition) + "\n");
        result.append(innerText);
        result.append(buildTag(HEAD, true) + "\n");

        return result.toString();
    }

    /**
     *  Creates HTML header-tag.
     *
     * @param  innerText  headertext
     * @param  size       Header Size between 1 and 5; 1 biggest - 5 smallest
     * @return            complete header-tag
     */
    public static String createHeader(String innerText, int size) {
        if (size < 1)
            size = 1;

        if (size > 5)
            size = 5;

        StringBuffer result = new StringBuffer();
        String tagDefinition = H + size;

        result.append(buildTag(tagDefinition));
        result.append(innerText);
        result.append(buildTag(H + size, true) + "\n");

        return result.toString();
    }

    /**
     *  Return hr-tag.
     *
     * @param  noshade  set false if you want a shade
     * @return          Description of the Return Value
     */
    public static String createHorizontalRule(boolean noshade) {
        StringBuffer result = new StringBuffer();
        String tagDefinition = HR;

        if (noshade)
            tagDefinition += "noshade=\"noshade\" ";

        result.append(buildTag(tagDefinition));
        result.append(buildTag(HR, true) + "\n");

        return result.toString();
    }

    /**
     *  return hr-tag with noshade set.
     *
     * @return    Description of the Return Value
     */
    public static String createHorizontalRule() {
        return createHorizontalRule(true);
    }

    /**
     *  Gets the html attribute of the HTMLFactory object
     *
     * @param  innerText  Description of the Parameter
     * @return            The html value
     */
    public static String createHtml(String innerText) {
        StringBuffer result = new StringBuffer();
        String tagDefinition = HTML;

        result.append(buildTag(tagDefinition) + "\n");
        result.append(innerText);
        result.append(buildTag(HTML, true) + "\n");

        return result.toString();
    }

    /**
     *  Description of the Method
     *
     * @param  innerText  Description of the Parameter
     * @return            Description of the Return Value
     */
    public static String createItalic(String innerText) {
        StringBuffer result = new StringBuffer();
        String tagDefinition = I;

        result.append(buildTag(tagDefinition));
        result.append(innerText);
        result.append(buildTag(I, true));

        return result.toString();
    }

    /**
     *  Gets the br attribute of the HTMLFactory object
     *
     * @return    The br value
     */
    public static String createLineBreak() {
        StringBuffer result = new StringBuffer();
        String tagDefinition = BR;

        result.append(buildTag(tagDefinition));
        result.append(buildTag(BR, true) + "\n");

        return result.toString();
    }

    /**
     *  Gets the listItem attribute of the HTMLFactory object
     *
     * @param  innerText  Description of the Parameter
     * @return            The listItem value
     */
    public static String createListItem(String innerText) {
        StringBuffer result = new StringBuffer();
        String tagDefinition = LI;

        result.append(buildTag(tagDefinition));
        result.append(innerText);
        result.append(buildTag(LI, true));

        return result.toString();
    }

    /**
     *  Creates area with no line breaking allowed.
     *
     * @param  innerText  text, where line break will be disabled
     * @return            complete nobr-tag
     */
    public static String createNobreak(String innerText) {
        StringBuffer result = new StringBuffer();
        String tagDefinition = NOBR;

        result.append(buildTag(tagDefinition));
        result.append(innerText);
        result.append(buildTag(NOBR, true));

        return result.toString();
    }

    /**
     *  Description of the Method
     *
     * @param  innerText  Description of the Parameter
     * @param  value      Description of the Parameter
     * @param  selected   Description of the Parameter
     * @return            Description of the Return Value
     */
    public static String createOption(
        String innerText,
        String value,
        boolean selected) {
        StringBuffer result = new StringBuffer();
        String tagDefinition = OPTION;

        tagDefinition += "value=\"" + value + "\" ";
        if (selected)
            tagDefinition += "selected=\"selected\" ";

        result.append(buildTag(tagDefinition));
        result.append(innerText);
        result.append(buildTag(OPTION, true));

        return result.toString();
    }

    /**
     *  Description of the Method
     *
     * @param  innerText  Description of the Parameter
     * @return            Description of the Return Value
     */
    public static String createOrderedList(String innerText) {
        return createList(innerText, true);
    }

    /**
     *  Gets the p attribute of the HTMLFactory object
     *
     * @param  innerText  Description of the Parameter
     * @return            The p value
     */
    public static String createParagraph(String innerText) {
        StringBuffer result = new StringBuffer();
        String tagDefinition = P;

        result.append("\n" + buildTag(tagDefinition));
        result.append(innerText);
        result.append(buildTag(P, true) + "\n");

        return result.toString();
    }

    /**
     *  Description of the Method
     *
     * @param  innerText  Description of the Parameter
     * @param  name       Description of the Parameter
     * @param  value      Description of the Parameter
     * @param  checked    Description of the Parameter
     * @return            Description of the Return Value
     */
    public static String createRadioButton(
        String innerText,
        String name,
        String value,
        boolean checked) {
        StringBuffer result = new StringBuffer();
        String tagDefinition = INPUT;

        tagDefinition += "type=\"radio\" ";
        tagDefinition += "name=\"" + name + "\" ";
        tagDefinition += "value=\"" + value + "\" ";
        if (checked)
            tagDefinition += "checked=\"checked\" ";

        result.append(buildTag(tagDefinition));
        result.append(innerText);
        result.append(buildTag(INPUT, true));

        return result.toString();
    }

    /**
     *  Description of the Method
     *
     * @param  adress     Description of the Parameter
     * @param  target     Description of the Parameter
     * @param  innerText  Description of the Parameter
     * @return            Description of the Return Value
     */
    public static String createReference(
        String innerText,
        String adress,
        String target) {
        StringBuffer result = new StringBuffer();
        String tagDefinition = A;

        tagDefinition += "href=\"" + adress + "\" ";
        tagDefinition += "target=\"" + target + "\" ";

        result.append(buildTag(tagDefinition));
        result.append(innerText);
        result.append(buildTag(BODY, true));

        return result.toString();
    }

    /**
     *  Description of the Method
     *
     * @param  value  Description of the Parameter
     * @return        Description of the Return Value
     */
    public static String createResetButton(String value) {
        StringBuffer result = new StringBuffer();
        String tagDefinition = INPUT;

        tagDefinition += "type=\"reset\" ";
        tagDefinition += "value=\"" + value + "\" ";
        result.append(buildTag(tagDefinition));
        result.append(buildTag(INPUT, true));

        return result.toString();
    }

    /**
     *  Description of the Method
     *
     * @param  innerText  Description of the Parameter
     * @param  name       Description of the Parameter
     * @param  size       Description of the Parameter
     * @param  multiple   Description of the Parameter
     * @return            Description of the Return Value
     */
    public static String createSelect(
        String innerText,
        String name,
        int size,
        boolean multiple) {
        StringBuffer result = new StringBuffer();
        String tagDefinition = SELECT;

        tagDefinition += "name=\"" + name + "\" ";
        tagDefinition += "size=\"" + size + "\" ";
        if (multiple)
            tagDefinition += "multiple=\"multiple\" ";

        result.append(buildTag(tagDefinition));
        result.append(innerText);
        result.append(buildTag(SELECT, true));

        return result.toString();
    }

    /**
     *  Description of the Method
     *
     * @param  innerText  Description of the Parameter
     * @return            Description of the Return Value
     */
    public static String createStrikeThrough(String innerText) {
        StringBuffer result = new StringBuffer();
        String tagDefinition = S;

        result.append(buildTag(tagDefinition));
        result.append(innerText);
        result.append(buildTag(S, true));

        return result.toString();
    }

    /**
     *  Description of the Method
     *
     * @param  innerText  Description of the Parameter
     * @return            Description of the Return Value
     */
    public static String createSub(String innerText) {
        StringBuffer result = new StringBuffer();
        String tagDefinition = SUB;

        result.append(buildTag(tagDefinition));
        result.append(innerText);
        result.append(buildTag(SUB, true));

        return result.toString();
    }

    /**
     *  Gets the submitButton attribute of the HTMLFactory object
     *
     * @param  value  Description of the Parameter
     * @return        The submitButton value
     */
    public static String createSubmitButtonCommon(String value) {
        StringBuffer result = new StringBuffer();
        String tagDefinition = INPUT;

        tagDefinition += "type=\"submit\" ";
        tagDefinition += "value=\"" + value + "\" ";
        result.append(buildTag(tagDefinition));
        result.append(buildTag(INPUT, true));

        return result.toString();
    }

    /**
     *  Description of the Method
     *
     * @param  imageSrc  Description of the Parameter
     * @return           Description of the Return Value
     */
    public static String createSubmitButtonGraphical(String imageSrc) {
        StringBuffer result = new StringBuffer();
        String tagDefinition = INPUT;

        tagDefinition += "type=\"image\" ";
        tagDefinition += "src=\"" + imageSrc + "\" ";
        result.append(buildTag(tagDefinition));
        result.append(buildTag(INPUT, true));

        return result.toString();
    }

    /**
     *  Description of the Method
     *
     * @param  innerText  Description of the Parameter
     * @return            Description of the Return Value
     */
    public static String createSup(String innerText) {
        StringBuffer result = new StringBuffer();
        String tagDefinition = SUP;

        result.append(buildTag(tagDefinition));
        result.append(innerText);
        result.append(buildTag(SUP, true));

        return result.toString();
    }

    /**
     *  Creates table-tag.
     *
     * @param  innerText                  text that stands between table-tags
     * @param  borderWidth                table-border-width
     * @param  tableWidth                 Description of the Parameter
     * @return                            Description of the Return Value
     * @throws  IllegalArgumentException  if 0 > tableWidth > 100 or borderWidth < 0
     */
    public static String createTable(
        String innerText,
        int tableWidth,
        int borderWidth)
        throws IllegalArgumentException {
        if (borderWidth < 0)
            throw new IllegalArgumentException("borderWidth must be positive value");
        if ((tableWidth < 0) || (tableWidth > 100))
            throw new IllegalArgumentException("tableWidth must be between 0 and 100");

        StringBuffer result = new StringBuffer();
        String tagDefinition = "";

        tagDefinition = TABLE + "WIDTH=\"" + tableWidth + "%\" ";
        tagDefinition += TABLE + "BORDER=\"" + borderWidth + "\" ";

        result.append("\n" + buildTag(tagDefinition));
        result.append(innerText);
        result.append(buildTag(TABLE, true) + "\n");

        return result.toString();
    }

    /**
     *  Description of the Method
     *
     * @param  innerText  Description of the Parameter
     * @param  width      Description of the Parameter
     * @return            Description of the Return Value
     */
    public static String createTableCell(String innerText, String width) {
        if (innerText.equalsIgnoreCase("")) {
            innerText = "&nbsp;";
        }

        StringBuffer result = new StringBuffer();
        String tagDefinition = TD;

        tagDefinition += "width=\"" + width + "\" ";

        result.append(buildTag(tagDefinition));
        result.append(innerText);
        result.append(buildTag(TD, true));

        return result.toString();
    }

    /**
     *  Description of the Method
     *
     * @param  innerText  Description of the Parameter
     * @return            Description of the Return Value
     */
    public static String createTableHeaderCell(String innerText) {
        if (innerText.equalsIgnoreCase("")) {
            innerText = "&nbsp;";
        }

        StringBuffer result = new StringBuffer();
        String tagDefinition = TH;

        result.append(buildTag(tagDefinition));
        result.append(innerText);
        result.append(buildTag(TH, true));

        return result.toString();
    }

    /**
     *  Description of the Method
     *
     * @param  innerText  Description of the Parameter
     * @return            Description of the Return Value
     */
    public static String createTableRow(String innerText) {
        StringBuffer result = new StringBuffer();
        String tagDefinition = TR;

        result.append("\n" + buildTag(tagDefinition));
        result.append(innerText);
        result.append(buildTag(TR, true) + "\n");

        return result.toString();
    }

    /**
     *  Description of the Method
     *
     * @param  innerText  Description of the Parameter
     * @param  name       Description of the Parameter
     * @param  cols       Description of the Parameter
     * @param  rows       Description of the Parameter
     * @param  readonly   Description of the Parameter
     * @return            Description of the Return Value
     */
    public static String createTextArea(
        String innerText,
        String name,
        int cols,
        int rows,
        boolean readonly) {
        StringBuffer result = new StringBuffer();
        String tagDefinition = TEXTAREA;

        tagDefinition += "name=\"" + name + "\" ";
        tagDefinition += "cols=\"" + cols + "\" ";
        tagDefinition += "rows=\"" + rows + "\" ";
        tagDefinition += "wrap=\"physical\" ";
        if (readonly)
            tagDefinition += "readonly=\"readonly\" ";

        result.append(buildTag(tagDefinition));
        result.append(innerText);
        result.append(buildTag(TEXTAREA, true));

        return result.toString();
    }

    /**
     *  Description of the Method
     *
     * @param  value      Description of the Parameter
     * @param  name       Description of the Parameter
     * @param  fieldSize  Description of the Parameter
     * @param  maxChar    Description of the Parameter
     * @param  readonly   Description of the Parameter
     * @return            Description of the Return Value
     */
    public static String createTextField(
        String value,
        String name,
        int fieldSize,
        int maxChar,
        boolean readonly) {
        StringBuffer result = new StringBuffer();
        String tagDefinition = INPUT;

        tagDefinition += "type=\"text\" ";
        tagDefinition += "value=\"" + value + "\" ";
        tagDefinition += "name=\"" + name + "\" ";
        tagDefinition += "size=\"" + fieldSize + "\" ";
        tagDefinition += "maxlength=\"" + maxChar + "\" ";
        if (readonly)
            tagDefinition += "readonly=\"readonly\" ";

        result.append(buildTag(tagDefinition));
        result.append(buildTag(INPUT, true));

        return result.toString();
    }

    /**
     *  Gets the title attribute of the HTMLFactory object
     *
     * @param  innerText  Description of the Parameter
     * @return            The title value
     */
    public static String createTitle(String innerText) {
        StringBuffer result = new StringBuffer();
        String tagDefinition = TITLE;

        result.append(buildTag(tagDefinition));
        result.append(innerText);
        result.append(buildTag(TITLE, true) + "\n");

        return result.toString();
    }

    /**
     *  Description of the Method
     *
     * @param  innerText  Description of the Parameter
     * @return            Description of the Return Value
     */
    public static String createUnOrderedList(String innerText) {
        return createList(innerText, false);
    }

    /**
     *  Description of the Method
     *
     * @param  innerText  Description of the Parameter
     * @return            Description of the Return Value
     */
    public static String createUnderline(String innerText) {
        StringBuffer result = new StringBuffer();
        String tagDefinition = U;

        result.append(buildTag(tagDefinition));
        result.append(innerText);
        result.append(buildTag(U, true));

        return result.toString();
    }

    /**
     *  Description of the Method
     *
     * @param  tagDefinition  Description of the Parameter
     * @param  closing        Description of the Parameter
     * @return                Description of the Return Value
     */
    protected static String buildTag(String tagDefinition, boolean closing) {
        tagDefinition = tagDefinition.trim();
        if (closing)
            return TAG_START + TAG_CLOSING + tagDefinition + TAG_END;
        else
            return TAG_START + tagDefinition + TAG_END;
    }

    /**
     *  Description of the Method
     *
     * @param  tagDefinition  Description of the Parameter
     * @return                Description of the Return Value
     */
    protected static String buildTag(String tagDefinition) {
        return buildTag(tagDefinition, false);
    }

    /**
     *  Gets the orderedList attribute of the HTMLFactory object
     *
     * @param  ordered    Description of the Parameter
     * @param  innerText  Description of the Parameter
     * @return            The orderedList value
     */
    protected static String createList(String innerText, boolean ordered) {
        StringBuffer result = new StringBuffer();
        String tagDefinition;

        if (ordered) {
            tagDefinition = OL;
        }

        else {
            tagDefinition = UL;
        }

        result.append(buildTag(tagDefinition) + "\n");
        result.append(innerText);
        if (ordered) {
            result.append(buildTag(OL, true) + "\n");
        }

        else {
            result.append(buildTag(UL, true) + "\n");
        }

        return result.toString();
    }
}