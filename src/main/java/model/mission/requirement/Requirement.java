package model.mission.requirement;

/**
 * This class defines an interface for a generic Requirement, used as a fulfillment condition by a Mission object too.
 */
public interface Requirement {
    // Non ho usato un'interfaccia parametrica perché poi sarebbe stato un casino nella classe Mission,
    // dato che possono esistere più requisiti anche di tipi diversi per una stessa missione

    /**
     * Checks whether the requirement is fulfilled with the given value.
     * @param status The value which should satisfy the requirement.
     * @return True if the requirement is fulfilled, false otherwise.
     */
    boolean isMet(Object status);
}