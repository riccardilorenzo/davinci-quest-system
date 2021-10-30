package servlet;

/**
 * Static class with no public constructors nor factory methods.
 * Only contains some useful constants (DB URLs for example).
 * @author TheMind
 *
 */
public class ServletUtils {
    /**
     * Secondary database JDBC connector string.
     */
    public static final String VITRUVIO_DB_URL = "YOUR_JDBC_CONNECTOR_STRING_GOES_HERE";

    /**
     * Primary database JDBC connector string.
     */
    public static final String LOCAL_DB_URL = "YOUR_JDBC_CONNECTOR_STRING_GOES_HERE";

    private ServletUtils() { }
}
