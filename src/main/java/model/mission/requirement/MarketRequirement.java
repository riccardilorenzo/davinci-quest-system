package model.mission.requirement;

public class MarketRequirement implements Requirement {
    private int amountToSell/*, amountSold*/;

    public MarketRequirement(int amountToSell/*, int amountSold*/) {
        this.amountToSell = amountToSell;
        //this.amountSold = amountSold;
    }

    /*public void setAmountSold(int amountSold) {
        this.amountSold = amountSold;
    }*/

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
