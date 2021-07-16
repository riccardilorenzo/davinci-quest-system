package servlet;

/**
 * Static class with no public constructors nor factory methods.
 * Only contains some useful constants (DB URL for example).
 * @author TheMind
 *
 */
class ServletUtils {
    public static final String URL = "jdbc:postgresql://localhost/edmcdavinci?user=postgres";

    private ServletUtils() { }
}
