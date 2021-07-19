package model.mission.requirement;

public record MarketRequirement(int amountToSell) implements Requirement {

    /**
     * @see Requirement#isMet(Object other)
     */
    @Override
    public boolean isMet(Object other) {
        if (other instanceof Integer val)
            return val >= amountToSell;
        return false;
    }

    @Override
    public String toString() {
        return "(Market) Richiesto: " + this.amountToSell;
    }
}
