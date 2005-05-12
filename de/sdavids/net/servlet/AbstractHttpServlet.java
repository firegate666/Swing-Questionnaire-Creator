package de.sdavids.net.servlet;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractHttpServlet
            extends HttpServlet {

    /**
     * Type-safe constant for the different MIME types.
     *
     * @author  Sebastian Davids <a href="mailto:sdavids@gmx.de">sdavids@gmx.de</a>
     * @version SD SD 1.0 [01-09-05 17:16:34]
     * @since   (01-07-21 17:10:30)
     */
    protected static class MIMEType {
        /** The HTML MIME type:  &quot;text/html&quot;. */
        public static final MIMEType HTML = new MIMEType("text/html");

        /** The XML MIME type:  &quot;text/xml&quot;.  */
        public static final MIMEType XML  = new MIMEType("text/xml");

        /** The binary GIF image MIME type:  &quot;image/gif&quot;. */
        public static final MIMEType GIF  = new MIMEType("image/gif");

        /** The binary PNG image MIME type:  &quot;image/png&quot;. */
        public static final MIMEType PNG  = new MIMEType("image/png");

        /**
         * The binary JPEG/JFIF image type; longer extension:
         * &quot;image/jpeg&quot;.
         * @see #JPG
        .*/
        public static final MIMEType JPEG = new MIMEType("image/jpeg");

        /** The binary JPEG/JFIF image  type:  &quot;image/jpg&quot;. */
        public static final MIMEType JPG  = new MIMEType("image/jpg");
        /** The MIME type' string. */
        String type_;

        /**
         * Construct and initialize a new MIMEType.
         *
         * @param    <code>type</code> the MIME type.
         */
        protected MIMEType(String type) {
            type_ = type;
        }

        /**
         * Answer the MIME type' string.
         *
         * @return    the string.
         */
        public String getType() {
            return type_;
        }
    }


/**
 * Does nothing, because this is an abstract class.
 */
protected AbstractHttpServlet() {
    super();
}


/**
 * Process incoming HTTP GET requests.
 *
 * <p>Requests are delegated to the <code>performTask</code> method.</p>
 *
 * @param     <code>req</code> an <code>HttpServletRequest</code> object that
 *            contains the request the client has made of the servlet.
 * @param     <code>res</code> an <code>HttpServletResponse</code> object that
 *              contains the response the servlet sends to the client.
 * @throws      <code>ServletException<code> if the request for the GET could not be
 *            handled.
 * @throws    <code>IOException</code> if an input or output error is detected when
 *              the servlet handles the request.
 * @see          javax.servlet.http.HttpServlet#doGet
 */
protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

    performTask(req, res);
}


/**
 * Process incoming HTTP POST requests .
 *
 * <p>Requests are delegated to the <code>performTask</code> method.</p>
 *
 * @param     <code>req</code> an <code>HttpServletRequest</code> object that
 *            contains the request the client has made of the servlet.
 * @param     <code>res</code> an <code>HttpServletResponse</code> object that
 *              contains the response the servlet sends to the client.
 * @throws      <code>ServletException<code> if the request for the GET could not be
 *            handled.
 * @throws    <code>IOException</code> if an input or output error is detected when
 *              the servlet handles the request.
 * @see       javax.servlet.http.HttpServlet#doPost
 */
protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

    performTask(req, res);
}


/**
 * Gathers the information from the HTML form and stores the name/value pairs
 * in a properties object.
 *
 * <p>If there multiple form fields with the same name, only the first one is
 * considered and included in the properties object.</p>
 *
 * @param      <code>req</code> an <code>HttpServletRequest</code> object that
 *              contains the request the client has made of the servlet.
 * @return    the properties.
 */
protected Properties getFormParameters(HttpServletRequest req) {
    Properties  result = new Properties();
    Enumeration en     = req.getParameterNames();
    String      paramName;
    String      paramValue;

    while (en.hasMoreElements()) {
        paramName  = (String) en.nextElement();
        paramValue = (String) req.getParameterValues(paramName)[0];
        result.put(paramName, paramValue);
    }
    return result;
}


/**
 * Answer an <code>ObjectOutputStream</code>;
 * the servlet's results are sent back to the client via this stream.
 *
 * @param    <code>res</code> an <code>HttpServletResponse</code> object that
 *            contains the response the servlet sends to the client.
 * @throws    <code>IOException</code> if an input or output error is detected when
 *            the servlet handles the request.
 */
protected ObjectOutputStream getObjectOutputStream(HttpServletResponse res)
            throws IOException {

    return new ObjectOutputStream(res.getOutputStream());
}


/**
 * Answer a <code>PrintWriter</code> with a HTML content;
 * the servlet's results are sent back to the client via this writer.
 *
 * @param    <code>res</code> an <code>HttpServletResponse</code> object that
 *            contains the response the servlet sends to the client.
 * @throws    <code>IOException</code> if an input or output error is detected when
 *            the servlet handles the request.
 */
protected PrintWriter getPrintWriter(HttpServletResponse res)
            throws IOException {

    return getPrintWriter(res, MIMEType.HTML);
}


/**
 * Answer a <code>PrintWriter</code> with the specified content MIME type;
 * the servlet's results are sent back to the client via this stream.
 *
 * @param    <code>res</code> an <code>HttpServletResponse</code> object that
 *            contains the response the servlet sends to the client.
 * @throws    <code>IOException</code> if an input or output error is detected when
 *            the servlet handles the request.
 */
protected PrintWriter getPrintWriter(HttpServletResponse res, MIMEType type)
            throws IOException {

    res.setContentType(type.getType());
    return res.getWriter();
}


/**
 * Process incoming requests for information.
 *
 * @param    <code>req</code> an <code>HttpServletRequest</code> object that
 *            contains the request the client has made of the servlet.
 * @param    <code>res</code> an <code>HttpServletResponse</code> object that
 *            contains the response the servlet sends to the client.
 * @throws    <code>ServletException</code> if the request could not be handled.
 * @throws    <code>IOException</code> if an input or output error is detected when
 *            the servlet handles the request.
 */
protected abstract void performTask(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException;
}