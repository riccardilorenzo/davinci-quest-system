package servlet;

/**
 * Static class with no public constructors nor factory methods.
 * Only contains some useful constants (DB URLs for example).
 * @author TheMind
 *
 */
public class ServletUtils {
    /**
     * edmcdavinci database JDBC connector string.
     */
    public static final String VITRUVIO_DB_URL = "jdbc:postgresql://localhost/edmcdavinci?user=postgres";

    /**
     * quest_system database JDBC connector string.
     */
    public static final String LOCAL_DB_URL = "jdbc:postgresql://localhost/quest_system?user=postgres";

    private ServletUtils() { }
}
